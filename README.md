
# 🚆 Train Ticket Booking System (Java Console Application)

A simple **Train Ticket Booking System** built using **Core Java** that simulates the basic functionality of railway reservation systems.
The application allows users to **sign up, log in, search trains, book seats, view bookings, and cancel tickets** through a console interface.

This project demonstrates the use of **Object-Oriented Programming (OOP), file-based data storage, and modular service design** in Java.

---

# 📌 Problem Statement

Railway reservation systems are essential for managing train travel efficiently.
Passengers need a simple way to:

* Register themselves
* Search available trains between stations
* Book seats
* View their bookings
* Cancel reservations when needed

Many beginners struggle to understand how such systems work internally.

This project solves that problem by implementing a **simplified train booking system** using **Core Java and JSON-based storage**, allowing developers to understand:

* How booking systems work
* How data is stored and retrieved
* How multiple components interact in a real application

---

# ✨ Features

The system supports the following operations:

### 👤 User Management

* User Sign Up
* Secure Login using password hashing
* Unique User IDs

### 🚆 Train Search

* Search trains between **source and destination stations**
* Display train route and station timings

### 💺 Seat Booking

* Visual seat layout (matrix format)
* Book available seats
* Prevent double booking
* Automatic ticket ID generation

### 🎫 Ticket Management

* View booked tickets
* Cancel tickets using Ticket ID

### 💾 Persistent Storage

Data is stored using **JSON files**:

* `users.json`
* `trains.json`

This allows the system to **retain data between program runs**.

---

# 🛠 Technologies Used

* **Java**
* **Jackson Library (JSON Processing)**
* **Object Oriented Programming**
* **File-based Local Database (JSON)**

---

# 📂 Project Structure

```
ticketBooking
│
└── app
    └── src
        └── main
            └── java
                └── ticket
                    └── booking
                        │
                        ├── App.java
                        │
                        ├── entities
                        │     ├── Train.java
                        │     ├── Ticket.java
                        │     └── User.java
                        │
                        ├── service
                        │     ├── UserBookingService.java
                        │     └── TrainService.java
                        │
                        ├── util
                        │     └── UserServiceUtil.java
                        │
                        └── localDB
                              ├── trains.json
                              └── users.json
```

---

# ⚙️ How the System Works

### 1️⃣ User Registration

A new user signs up by providing a username and password.
The password is **hashed before storing** for basic security.

---

### 2️⃣ Login

Users log in using their credentials.
The system verifies the hashed password before granting access.

---

### 3️⃣ Train Search

Users can search trains by entering:

```
Source Station
Destination Station
```

The system filters trains whose routes match the given stations.

---

### 4️⃣ Seat Booking

Once a train is selected:

* The system displays the seat layout
* Users choose **row and column**
* If the seat is available, a ticket is generated

Example:

```
0 0 0 0 0 0
0 0 0 0 0 0
0 1 0 0 0 0
0 0 0 0 0 0
```

`0` → Available
`1` → Booked

---

### 5️⃣ Ticket Generation

When a seat is booked:

* A **unique Ticket ID** is generated
* The ticket is linked to the user

Example:

```
Seat booked successfully
Ticket ID: T1719952334231
```

---

### 6️⃣ Ticket Cancellation

Users can cancel a ticket using the **Ticket ID**.

---

# ▶️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/ShraddhaYewale/train-ticket-booking-system.git
```

### 2. Open in IntelliJ / Eclipse

### 3. Run

```
App.java
```

---

# 📷 Sample Console Flow

```
Running Train Booking System

1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
```

---

# 📚 Learning Outcomes

This project helps understand:

* Java OOP design
* Service layer architecture
* JSON file persistence
* Simple authentication system
* Real-world booking system logic

---



1️⃣ UML Class Diagram
+------------------+
|       App        |
+------------------+
| main()           |
+------------------+
          |
          ▼
+---------------------------+
|    UserBookingService     |
+---------------------------+
| - userList : List<User>   |
| - trains : List<Train>    |
| - user : User             |
+---------------------------+
| loginUser()               |
| signUp()                  |
| getTrains()               |
| fetchBookings()           |
| bookTrainSeat()           |
| cancelBooking()           |
+---------------------------+
          |
          ▼
+------------------+      +------------------+
|      User        |      |      Train       |
+------------------+      +------------------+
| name             |      | trainId          |
| password         |      | trainNo          |
| hashedPassword   |      | seats            |
| ticketsBooked    |      | stationTimes     |
| userId           |      | stations         |
+------------------+      +------------------+
| printTickets()   |      | getSeats()       |
+------------------+      +------------------+
          |
          ▼
+------------------+
|      Ticket      |
+------------------+
| ticketId         |
| trainId          |
| row              |
| column           |
+------------------+
        
Output :Running Train Booking System

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
1
Enter username
Shraddha
Enter password
Sky123
Signup successful

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
2
Enter username
Shraddha
Enter password
Sky123
Login successful

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
3
No tickets booked.

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
4
Enter source station
pune
Enter destination station
mumbai
1 Train id : T101
pune : 06:00:00
mumbai : 09:00:00
surat : 13:00:00
delhi : 22:00:00
Select train
1

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
5
0 0 0 0 0 0 
0 0 0 0 0 0 
0 0 0 0 0 0 
0 0 0 0 0 0 
Enter row
2
Enter column
2
Seat booked successfully
Ticket ID: T1772815141645

Choose option
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit
# 🚀 Possible Future Improvements

* Add **PNR number generation**
* Add **multiple train coaches**
* Implement **seat availability by date**
* Build a **REST API using Spring Boot**
* Add **GUI using JavaFX**
* Implement **payment integration**

---

# 👨‍💻 Author

Developed as a **learning project to understand backend system design in Java**.

If you found this project helpful, feel free to ⭐ the repository.

---
