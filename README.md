# Encryption Service Project

This project provides a REST API for encrypting and decrypting JSON data using a combination of AES (Advanced Encryption Standard) and RSA (Rivest–Shamir–Adleman) encryption algorithms. AES is used for encrypting data due to its high performance, while RSA is used for encrypting the AES key itself for secure transmission.

## Project Structure

- **Controller**: `EncryptionController` - Provides endpoints for encrypting and decrypting data.
- **Service**: `EncryptionService` - Handles encryption and decryption logic.
- **Utilities**:
  - `AESUtil` - Utility class for AES key generation, encryption, and decryption.
  - `RSAUtil` - Utility class for RSA key generation, encryption, and decryption.

## Features

- **AES Encryption**: Encrypts data with AES using a dynamically generated key.
- **RSA Encryption**: Encrypts the AES key with RSA, allowing secure transmission of the AES key.
- **Hybrid Encryption**: Combines AES and RSA for secure data transfer.
- **REST API**: Provides endpoints for encrypting and decrypting data.

---

## Getting Started

### Prerequisites

Ensure you have the following installed:

- **Java**: Java Development Kit (JDK) 17 or higher
- **Maven**: For building the project
- **Spring Boot**: Framework used for creating stand-alone, production-grade Spring-based applications

### Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd encryption-service
   ```

2. **Build the Project**:
   Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   Start the application:
   ```bash
   mvn spring-boot:run
   ```

4. **Accessing the API**:
   The API will be available at `http://localhost:8080`.

### Endpoints

- **Encrypt Data**  
  `POST /encrypt`  
  - **Description**: Encrypts JSON data using AES and encrypts the AES key using RSA.
  - **Request Body**: JSON data to be encrypted.
  - **Response**: JSON containing `encryptedData` and `encryptedKey`.

- **Decrypt Data**  
  `POST /decrypt`  
  - **Description**: Decrypts the JSON data using the encrypted AES key.
  - **Request Body**: JSON with `encryptedData` and `encryptedKey`.
  - **Response**: The original JSON data.

---

## Example Usage

1. **Encrypt Request**:
   ```bash
   curl -X POST http://localhost:8080/encrypt -H "Content-Type: application/json" -d '{"message": "Hello, World!"}'
   ```
   
   **Response**:
   ```json
   {
     "encryptedData": "<encrypted data>",
     "encryptedKey": "<encrypted AES key>"
   }
   ```

2. **Decrypt Request**:
   ```bash
   curl -X POST http://localhost:8080/decrypt -H "Content-Type: application/json" -d '{ "encryptedData": "<encrypted data>", "encryptedKey": "<encrypted AES key>" }'
   ```
   
   **Response**:
   ```json
   {
     "message": "Hello, World!"
   }
   ```

---

## Technical Details

- **AES Key Size**: 256 bits
- **RSA Key Size**: 2048 bits
- **Libraries**: Uses Java's `javax.crypto` package for encryption operations

---

## Additional Notes

- **Security Note**: Ensure secure storage of RSA private keys in production.
- **Customization**: AES and RSA configurations can be customized as needed.

---

