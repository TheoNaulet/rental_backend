
# 🏡 Rental Backend API

Welcome to the **Rental Backend API**, a project developed in **Spring Boot** to handle property rentals and user interactions in a seasonal rental system. This backend provides APIs for authentication, user management, property listings, and messaging, integrating with JWT for security and Cloudinary for image handling.

---

## 📝 Features

### 🔒 Authentication
- Register users with secure password hashing (BCrypt).
- Login with email and password to receive a **JWT token**.
- Protect API endpoints with role-based access.

### 🏠 Property Management
- List, create, and manage property rentals.
- Associate properties with specific users (owners).

### 💬 Messaging System
- Enable users to send and retrieve messages for specific properties.

---

## ⚙️ Technologies Used

- **Java** (Spring Boot): Backend framework.
- **JWT**: Token-based authentication.
- **Spring Security**: Secures API endpoints.
- **Hibernate/JPA**: Database ORM.
- **MySQL**: Relational database.
- **Cloudinary**: Image storage and management.
- **Swagger/OpenAPI**: API documentation.
- **Dotenv**: Environment variable management.
- **Mockoon/Postman**: Mocking and API testing.

---

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven**
- **MySQL**
- An IDE (e.g., IntelliJ IDEA, Eclipse)

---

### 📥 Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/username/rental-backend.git
   cd rental-backend
   ```

2. **Retrieve the frontend repository**
   - Clone the frontend repository using the following command:
     ```bash
     git clone https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git
     ```

3. **Configure the environment variables**
   - Create a `.env` file in the root directory with the following variables:
     ```
     JWT_SECRET_KEY=your_jwt_secret_key
     CLOUDINARY_API_KEY=your_cloudinary_api_key
     CLOUDINARY_API_SECRET=your_cloudinary_api_secret
     CLOUDINARY_CLOUD_NAME=your_cloudinary_cloud_name
     ```

4. **Set up the database**
   - Create a new MySQL database named `rental_backend`.
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/rental_backend
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

5. **Install dependencies**
   ```bash
   mvn clean install
   ```

6. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

7. **Access the API**
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - API Documentation: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

---

## 🛠️ Project Architecture

### 📂 Folder Structure
```
src/main/java/com/example/rental_backend/
├── config/         # Configuration files (Security, Cloudinary)
├── controller/     # REST controllers for handling API requests
├── dto/            # Data Transfer Objects for API communication
├── model/          # Entity classes representing database tables
├── repository/     # Repositories for database operations
├── service/        # Business logic and services
└── RentalBackendApplication.java  # Main application entry point
```

### 🏗️ Layers of the Application
- **Controller Layer**: Handles HTTP requests and responses.
- **Service Layer**: Contains business logic and service methods.
- **Repository Layer**: Communicates with the database.
- **Model Layer**: Defines the database schema.

---

## 🌟 Key Endpoints

### 🔑 Authentication
- `POST /api/auth/register`: Register a new user.
- `POST /api/auth/login`: Login and receive a JWT token.
- `GET /api/auth/me`: Retrieve details of the authenticated user.

### 🏠 Rentals
- `GET /api/rentals`: List all properties.
- `POST /api/rentals`: Add a new property (owner only).
- `PUT /api/rentals`: Update a property.
- `GET /api/rentals/id`: Get a property by its id.

### 💬 Messages
- `POST /api/messages`: Send a message about a rental.

---

## 📜 License

This project is licensed under the [MIT License](LICENSE).

---

## 👨‍💻 Author

**Théo Naulet**  
🎯 Developer passionate about crafting clean and scalable applications.  
