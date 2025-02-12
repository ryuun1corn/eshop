# Eshop
Yudayana Arif Prasojo (2306215160)

## Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Setelah mengikuti tutorial dan mengerjakan exercise yang terdapat pada modul, saya mempelajari beberapa hal baru:
1. Memberikan nama yang jelas pada variabel. Memberikan nama variabel yang jelas (walaupun akan menjadi panjang) sangat penting untuk meningkatkan keterbacaan dan pemahaman kode. Variabel yang diberi nama secara ambigu atau singkat dapat membingungkan programmer yang akan membaca kode tersebut (termasuk saya sendiri). 
2. Menggunakan komen dengan minimum dan seperlunya saja. Komentar sebaiknya digunakan untuk menjelaskan kenapa suatu hal dilakukan, bukan apa yang dilakukan. Kode yang sudah ditulis dengan jelas tidak memerlukan komentar yang berlebihan. Hal ini dikarenakan kode yang baik akan terbaca seperti prosa yang indah.
3. Memanfaatkan fungsi yang sudah ada untuk fungsi lain dengan pola serupa. Daripada menulis kode yang berulang (redundant), lebih baik memanfaatkan fungsi yang sudah ada untuk menghindari code duplication. Hal ini meningkatkan reusability, membuat kode lebih mudah dikelola, dan mengurangi kemungkinan bug. Contohnya terdapat pada method findOne yang digunakan kembali di method edit dan delete
4. Mengikuti prinsip Single Responsibility Principle. Single Responsibility Principle (SRP) menyatakan bahwa setiap kelas atau fungsi hanya boleh memiliki satu alasan untuk berubah. Artinya, satu kelas hanya harus menangani satu tanggung jawab spesifik. Hal ini terlihat dari source code dimana terdapat class repository dan service dengan tanggung jawabnya masing-masing.

## Reflection 2
> 1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

Setelah menulis unit test, saya baru menyadari seberapa pentingnya testing aplikasi, terutama dalam skala enterprise. Dengan melakukan testing, developer dapat meminimalisir adanya bug dan error terhadap kode yang mereka buat karena menulis testing memastikan developer untuk memikirkan semua skenario yang ada. 

Tidak ada metrik khusus yang menandakan jumlah unit test yang baik untuk sebuah program karena hal tersebut tergantung terhadap kompleksitas program yang dites. Namun, sebuah aturan yang baik adalah untuk memastikan setiap method dites untuk menghasilkan output yang sesuai. Kemudian tulis juga tes yang tidak sesuai use-case seperti biasanya. Ketika semua skenario sudah dilewati, maka dapat dikatakan bahwa unit tests tersebut sudah cukup untuk memverifikasi program.

Jika terdapat 100% code coverage, bukan berarti bahwa code tersebut tidak memiliki bug ataupun error. Hal ini dikarenakan code coverage hanya menghitung berdasarkan eksekusi, bukan kebenaran logika program. Akibatnya, code coverage tidak dapat memastikan bahwa kode tidak memiliki bug atau error.

> 2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.  
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! 

Jika diharuskan membuat functional test class baru dengan prosedur setup dan variabel yang sama, maka kode tersebut tidak termasuk kode yang bersih. Hal ini disebabkan oleh prinsip DRY (Don't Repeat Yourself) yang berarti seharusnya kode tidak seharusnya ditulis ulang kembali. Solusi yang memungkinkan adalah untuk membuat class yang mengandung logic setup yang bersifat reusable dan membuat functional test yang relevan untuk inherit dari class setup tersebut.



