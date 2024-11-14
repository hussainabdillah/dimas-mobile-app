package com.seisme.dimas.ui.screens.profileScreen.addMemberScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.seisme.dimas.data.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _users = MutableLiveData<List<UserData>>()
    val users: LiveData<List<UserData>> = _users

    private val _members = MutableLiveData<List<UserData>>()
    val members: LiveData<List<UserData>> = _members

    private val _userDataCurrentLogin = MutableLiveData<UserData?>()
    val userDataCurrentLogin: LiveData<UserData?> = _userDataCurrentLogin

    init {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    _userDataCurrentLogin.value = document.toObject(UserData::class.java)
                }
                .addOnFailureListener {
                    // Handle error
                }
        }
    }

    // fetch semua user
    fun fetchUsers() {
        firestore.collection("users")
            .limit(10)  // Batasi 10 user
            .get()
            .addOnSuccessListener { result ->
                val userList = result.mapNotNull { document ->
                    val user = document.toObject(UserData::class.java)
                    user.documentId = document.id  // Set document ID
                    user
                }
                _users.value = userList
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }

    // fetch list member user yang sedang login
    fun fetchMembers() {
        val userId = auth.currentUser?.uid ?: return
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Ambil daftar anggota (sebagai list of maps yang bisa dikonversi menjadi list of UserData)
                    val members = document.toObject(UserData::class.java)?.members ?: emptyList()

                    // Konversi setiap item di members ke UserData
                    val memberList = members.mapNotNull { memberMap ->
                        // Konversi dari Map<String, Any> ke UserData
                        try {
                            val memberJson = Gson().toJson(memberMap)  // Konversi map ke JSON
                            Gson().fromJson(memberJson, UserData::class.java)  // Konversi JSON ke UserData
                        } catch (e: Exception) {
                            Log.e("FetchMembersError", "Error converting member map to UserData", e)
                            null
                        }
                    }

                    // Update nilai LiveData atau State dengan daftar anggota
                    _members.value = memberList
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FetchUserError", "Error fetching user document", exception)
            }
    }


    fun addMember(userData: UserData) {
        val currentUserId = auth.currentUser?.uid ?: return
        val currentUserRef = firestore.collection("users").document(currentUserId)

        currentUserRef.update("members", FieldValue.arrayUnion(userData))
            .addOnSuccessListener {
                // Member added successfully
            }
            .addOnFailureListener {
                // Handle error
            }
    }

    fun addMemberBothUsers(userData: UserData) {
        val currentUserId = auth.currentUser?.uid ?: return
        val currentUserRef = firestore.collection("users").document(currentUserId)
        val targetUserRef = firestore.collection("users").document(userData.documentId)

        firestore.runTransaction { transaction ->
            // Ambil snapshot dari kedua user
            val currentUserSnapshot = transaction.get(currentUserRef)
            val targetUserSnapshot = transaction.get(targetUserRef)

            // Ambil daftar members dari masing-masing user
            val currentUserMembers = (currentUserSnapshot.get("members") as? List<HashMap<String, Any>>)
                ?.mapNotNull { memberMap ->
                    try {
                        UserData(
                            documentId = memberMap["documentId"] as String,
                            email = memberMap["email"] as String,
                            username = memberMap["username"] as String,
                            contact = memberMap["contact"] as String,
                            gender = memberMap["gender"] as String
                        )
                    } catch (e: Exception) {
                        null
                    }
                }?.toMutableList() ?: mutableListOf()

            val targetUserMembers = (targetUserSnapshot.get("members") as? List<HashMap<String, Any>>)
                ?.mapNotNull { memberMap ->
                    try {
                        UserData(
                            documentId = memberMap["documentId"] as String,
                            email = memberMap["email"] as String,
                            username = memberMap["username"] as String,
                            contact = memberMap["contact"] as String,
                            gender = memberMap["gender"] as String
                        )
                    } catch (e: Exception) {
                        null
                    }
                }?.toMutableList() ?: mutableListOf()

            // Tambahkan target user ke members dari current user jika belum ada
            if (!currentUserMembers.any { it.documentId == userData.documentId }) {
                currentUserMembers.add(userData)
                transaction.update(currentUserRef, "members", currentUserMembers)
            }

            // Tambahkan current user ke members dari target user jika belum ada
            val currentUserData = userDataCurrentLogin.value?.let {
                UserData(
                    documentId = currentUserId,
                    email = auth.currentUser?.email ?: "",
                    username = it.username, // Sesuaikan dengan data yang dimiliki current user
                    contact = it.contact, // Sesuaikan dengan data yang dimiliki current user
                    gender = it.gender // Sesuaikan dengan data yang dimiliki current user
                )
            }
            if (!targetUserMembers.any { it.documentId == currentUserId }) {
                if (currentUserData != null) {
                    targetUserMembers.add(currentUserData)
                }
                transaction.update(targetUserRef, "members", targetUserMembers)
            }
        }.addOnSuccessListener {
            Log.d("AddMember", "Member successfully added in both users.")
        }.addOnFailureListener { exception ->
            Log.e("AddMemberError", "Failed to add member: ", exception)
        }
    }


    fun sendMemberRequest(targetId: String) {
        val requesterId = auth.currentUser?.uid ?: return
        val memberRequest = hashMapOf(
            "requesterId" to requesterId,
            "targetId" to targetId,
            "status" to "pending",
            "timestamp" to com.google.firebase.Timestamp.now()
        )

        FirebaseFirestore.getInstance().collection("member_requests")
            .add(memberRequest)
            .addOnSuccessListener {
                Log.d("MemberRequest", "Request sent successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("MemberRequest", "Error sending request", e)
            }
    }

    // target id yang harusnya saat ini login
    fun fetchUsersByRequesterId() {
        val targetId = "vTkh9u6UeeTX6mS4yfQX5CzHqWF2"
        val firestore = FirebaseFirestore.getInstance()

        // Langkah 1: Ambil dokumen dari `member_requests` untuk mendapatkan `requesterId`
        firestore.collection("member_requests")
            .whereEqualTo("targetId", targetId)
            .whereEqualTo("status", "pending")  // Hanya ambil permintaan yang pending
            .get()
            .addOnSuccessListener { result ->
                val requesterIds = result.mapNotNull { it.getString("requesterId") }

                // Langkah 2: Ambil data user berdasarkan `requesterId`
                firestore.collection("users")
                    .whereIn("userId", requesterIds)  // Gunakan daftar requesterIds untuk filter
                    .get()
                    .addOnSuccessListener { usersResult ->
                        val userList = usersResult.mapNotNull { document ->
                            val user = document.toObject(UserData::class.java)
                            user.documentId = document.id  // Set document ID
                            user
                        }
                        _users.value = userList
                    }
                    .addOnFailureListener { exception ->
                        // Handle error for fetching users
                        Log.e("FetchUsersError", "Error fetching users by requesterId", exception)
                    }
            }
            .addOnFailureListener { exception ->
                // Handle error for fetching member_requests
                Log.e("FetchRequestsError", "Error fetching member_requests", exception)
            }
    }


    fun respondToRequest(requestId: String, accept: Boolean, requesterId: String, targetId: String) {
        val newStatus = if (accept) "accepted" else "declined"

        // Update status di collection "member_requests"
        FirebaseFirestore.getInstance().collection("member_requests").document(requestId)
            .update("status", newStatus)
            .addOnSuccessListener {
                if (accept) {
                    // Jika diterima, tambahkan requesterId ke daftar anggota targetId
                    addMember(requesterId, targetId)
                }
                Log.d("MemberRequest", "Request $newStatus successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("MemberRequest", "Error updating request", e)
            }
    }

    // Menambahkan anggota ke collection "members"
    private fun addMember(userId: String, memberId: String) {
        // Tambahkan memberId ke dalam array "members" di dokumen userId
        val userRef = FirebaseFirestore.getInstance().collection("members").document(userId)
        userRef.update("members", FieldValue.arrayUnion(memberId))
            .addOnSuccessListener {
                // Tambahkan userId ke daftar anggota di dokumen memberId
                FirebaseFirestore.getInstance().collection("members").document(memberId)
                    .update("members", FieldValue.arrayUnion(userId))
                Log.d("MemberAddition", "Members updated successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("MemberAddition", "Error adding member", e)
            }
    }

    fun observeMemberRequests(targetId: String, onUpdate: (List<DocumentSnapshot>) -> Unit) {
        FirebaseFirestore.getInstance().collection("member_requests")
            .whereEqualTo("targetId", targetId)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("ObserveRequests", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshots != null && !snapshots.isEmpty) {
                    onUpdate(snapshots.documents)
                }
            }
    }

}
