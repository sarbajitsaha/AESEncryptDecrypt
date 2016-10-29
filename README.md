AESEncryptDecrypt
================

[![](https://jitpack.io/v/sarbajitsaha/AESEncryptDecrypt.svg)](https://jitpack.io/#sarbajitsaha/AESEncryptDecrypt)


Simple Android library to perform AES encryption and decryption.

It has

 * 256-bit AES key
 * PKCS7Padding
 * Blank/Empty IV **(default*)**

## SetUp

Add to top level *gradle.build* file

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Add to app module *gradle.build* file
```gradle
dependencies {
	compile 'com.github.sarbajitsaha:AESEncryptDecrypt:1.0.0'
}
```

# Usage

## Encrypt

```java
    String password = "password";
    String message = "hello";
    try
    {
        AES.setKey(password);
        String encryptedMessage = AES.encrypt(message);
    }
    catch (GeneralSecurityException e)
    {
        e.printStackTrace();
    }
```

## Decrypt

```java
    String password = "password";
    String encryptedMessage = "ae5dtBC+LdKNJX+PAoEZSA==";
    try
    {
        AES.setKey(password);
        String message = AES.decrypt(message);
    }
    catch (GeneralSecurityException e)
    {
        e.printStackTrace();
    }
```



#Contributing

I welcome pull requests, issues and feedback.  

- Fork it
- Create your feature branch (git checkout -b my-new-feature)
- Commit your changes (git commit -am 'Added some feature')
- Push to the branch (git push origin my-new-feature)
- Create new Pull Request


##Licence

    Copyright (c) 2016 Sarbajit Saha
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and limitations under the License.
