# AES Encryption Example (Symmetric Encryption)

This example demonstrates **AES (Advanced Encryption Standard)** encryption and decryption in Java using the **CBC mode** with **PKCS5Padding**.

---

## 🔑 Key Concepts

### 🧠 What is AES?
AES is a **symmetric block cipher**, meaning it uses the **same key** to encrypt and decrypt data. It is one of the most widely used encryption algorithms.

---

### 🔁 What is CBC Mode?

- **CBC** = Cipher Block Chaining.
- Each plaintext block is XOR'd with the previous **ciphertext** block before encryption.
- Requires an **Initialization Vector(IV)** for the **first block**, to prevent deterministic output plaintexts. 

---

### 🧪 What is an Initialization Vector (IV)?
- A **random, non-secret value** used in block cipher modes like CBC.
- Ensures that identical plaintexts encrypt to different ciphertexts, enhancing security
- Must be the same during encryption.

---
![image](https://github.com/user-attachments/assets/a71dea26-e4fb-4507-b010-c01525c91cb5)
---
## 🧱 What is Padding?

- AES only works on **fixed-size blocks** (128 bits = 16 bytes).
- If your plaintext isn't a multiple of 16 bytes, it must be **padded**
- We use 'PKCS5Padding', which appends bytes indicating how many bytes were added.

  Example: '"ABC"' → "ABC\x0D\x0D\x0D..."' (13 bytes added)

---

### 🔐 What Does This Example Do?

- Generates a **random 256-bit AES key**
- Generates a **random IV**
- Encrypts a plaintext message
- Decrypts it back to verify correctness
- Uses **Base64** to make binary outputs readable

---

### 🛠 Example Output

AES Key: A2FxwKb9nPvMYGLO+ZJh3oTyuhKHq2atmv6YMj6gBtA=

IV: kB52nR0TYVY19Seyq0XLBw==

Encrypted Text: pMPK2gnKXHcPSKgwOboz+w==

Decrypted Text: Hello, AES encryption!

---

## ⚠️ Security Notes

- Never reuse the same **key + IV** combination for encrypting multiple messages.
- AES in CBC mode is vulnerable to **padding oracle attacks** if not used properly (insecure error handling).
- For production use, consider **AES-GCM**, which provides both **confidentiality and integrity**

---

> ✨ This project is for **educational purposes only** and is not hardened for production environments.
