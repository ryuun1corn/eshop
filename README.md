# Eshop
Yudayana Arif Prasojo (2306215160)

[![Continuous Integration (CI)](https://github.com/ryuun1corn/eshop/actions/workflows/ci.yml/badge.svg)](https://github.com/ryuun1corn/eshop/actions/workflows/ci.yml)
[![Scorecard supply-chain security](https://github.com/ryuun1corn/eshop/actions/workflows/scorecard.yml/badge.svg)](https://github.com/ryuun1corn/eshop/actions/workflows/scorecard.yml)
[![CodeQL](https://github.com/ryuun1corn/eshop/actions/workflows/codeql.yml/badge.svg)](https://github.com/ryuun1corn/eshop/actions/workflows/codeql.yml)
[![pmd](https://github.com/ryuun1corn/eshop/actions/workflows/pmd.yml/badge.svg)](https://github.com/ryuun1corn/eshop/actions/workflows/pmd.yml)
[![Dependabot Updates](https://github.com/ryuun1corn/eshop/actions/workflows/dependabot/dependabot-updates/badge.svg)](https://github.com/ryuun1corn/eshop/actions/workflows/dependabot/dependabot-updates)

### Project URL
<a href="https://confused-rosalia-adpro-ryuun1corn-3ce73902.koyeb.app/" target="_blank">Access the website here!</a>

## Table of Contents
1. [Introduction](#Eshop)
2. [Project URL](#project-url)
3. [Week 01 Reflections](#week-01-reflections)
    - 4.1 [Reflection 1: Code Quality and Clean Code Principles](#reflection-1-code-quality-and-clean-code-principles)
    - 4.2 [Reflection 2: Unit Testing and Code Coverage](#reflection-2-unit-testing-and-code-coverage)
4. [Week 02 Reflections](#week-02-reflections)
    - 5.1 [Reflection: CI/CD and Code Quality Issues](#reflection-cicd-and-code-quality-issues)


## Week 02 Reflections

### Reflection: CI/CD and Code Quality Issues
> You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.
> 1. List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
> 2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Isu-isu kualitas kode yang saya perbaiki pada exercise ini beserta strategi yang saya implementasikan adalah sebagai berikut:
1. Memastikan workflow Github Actions mengikut security best practices dengan menerapkan "Pinned Dependencies" untuk memastikan versi dari setiap Actions yang ada terikat pada suatu commit, bukan mengikut latest update. Hal ini dilakukan untuk menghindari terjadinya breaking atau security changes terhadap Actions yang digunakan.
2. Menerapkan teknik Pinned Dependencies kepada container images yang terdapat pada Dockerfile.
3. Menambahkan Actions Harden Runner untuk memastikan Github Actions aman dari serangan seperti serangan supply chain, malicious dependencies, dan privilege escalation.
4. Menambahkan branch protection dengan memastikan tidak terjadinya force push atau deletion terhadap branch main, serta memastikan setiap pull request yang ada harus di-review terlebih dahulu.
5. Menambahkan workflow berupa dependency update tool (Dependabot) untuk mendeteksi dependencies yang out-of-date.
6. Menambahkan alat code scanner (SAST) baru yaitu CodeQL untuk menghindari bugs dalam aplikasi.
7. Menambahkan LICENSE dan SECURITY.md untuk menjelaskan legalitas yang dapat dilakukan pada project ini dan cara berkomunikasi dengan maintainer untuk melaporkan isu-isu keamanan.

Menurut saya, implementasi CI/CD yang sudah saya terapkan melalui Github Actions dan Koyeb telah memenuhi definisi dari Continuous Integration dan Continuous Deployment. Hal ini dikarenakan project saya sudah menerapkan Continuous Integration, yaitu membuat build secara otomatis dan melakukan testing terhadapnya. Alat-alat yang bertanggung jawab dalam melakukan ini adalah CodeQL, OSSF Scorecard, PMD, dan JaCoCo beserta Unit Test dalam aplikasi.

Project saya juga sudah dilengkapi Continuous Deployment dengan melakukan deployment secara otomatis ketika terjadi push terhadap branch main. Platform yang saya gunakan untuk CD adalah Koyeb, dilengkapi dengan Dockerfile untuk kebebasan yang lebih leluasa dalam memilih environment deployment yang digunakan. 

## Week 01 Reflections

### Reflection 1: Code Quality and Clean Code Principles
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code.

Setelah mengikuti tutorial dan mengerjakan exercise yang terdapat pada modul, saya mempelajari beberapa hal baru:
1. Memberikan nama yang jelas pada variabel. Memberikan nama variabel yang jelas (walaupun akan menjadi panjang) sangat penting untuk meningkatkan keterbacaan dan pemahaman kode. Variabel yang diberi nama secara ambigu atau singkat dapat membingungkan programmer yang akan membaca kode tersebut (termasuk saya sendiri). 
2. Menggunakan komen dengan minimum dan seperlunya saja. Komentar sebaiknya digunakan untuk menjelaskan kenapa suatu hal dilakukan, bukan apa yang dilakukan. Kode yang sudah ditulis dengan jelas tidak memerlukan komentar yang berlebihan. Hal ini dikarenakan kode yang baik akan terbaca seperti prosa yang indah.
3. Memanfaatkan fungsi yang sudah ada untuk fungsi lain dengan pola serupa. Daripada menulis kode yang berulang (redundant), lebih baik memanfaatkan fungsi yang sudah ada untuk menghindari code duplication. Hal ini meningkatkan reusability, membuat kode lebih mudah dikelola, dan mengurangi kemungkinan bug. Contohnya terdapat pada method findOne yang digunakan kembali di method edit dan delete
4. Mengikuti prinsip Single Responsibility Principle. Single Responsibility Principle (SRP) menyatakan bahwa setiap kelas atau fungsi hanya boleh memiliki satu alasan untuk berubah. Artinya, satu kelas hanya harus menangani satu tanggung jawab spesifik. Hal ini terlihat dari source code dimana terdapat class repository dan service dengan tanggung jawabnya masing-masing.

### Reflection 2: Unit Testing and Code Coverage
> 1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

Setelah menulis unit test, saya baru menyadari seberapa pentingnya testing aplikasi, terutama dalam skala enterprise. Dengan melakukan testing, developer dapat meminimalisir adanya bug dan error terhadap kode yang mereka buat karena menulis testing memastikan developer untuk memikirkan semua skenario yang ada. 

Tidak ada metrik khusus yang menandakan jumlah unit test yang baik untuk sebuah program karena hal tersebut tergantung terhadap kompleksitas program yang dites. Namun, sebuah aturan yang baik adalah untuk memastikan setiap method dites untuk menghasilkan output yang sesuai. Kemudian tulis juga tes yang tidak sesuai use-case seperti biasanya. Ketika semua skenario sudah dilewati, maka dapat dikatakan bahwa unit tests tersebut sudah cukup untuk memverifikasi program.

Jika terdapat 100% code coverage, bukan berarti bahwa code tersebut tidak memiliki bug ataupun error. Hal ini dikarenakan code coverage hanya menghitung berdasarkan eksekusi, bukan kebenaran logika program. Akibatnya, code coverage tidak dapat memastikan bahwa kode tidak memiliki bug atau error.

> 2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.  
> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! 

Jika diharuskan membuat functional test class baru dengan prosedur setup dan variabel yang sama, maka kode tersebut tidak termasuk kode yang bersih. Hal ini disebabkan oleh prinsip DRY (Don't Repeat Yourself) yang berarti seharusnya kode tidak seharusnya ditulis ulang kembali. Solusi yang memungkinkan adalah untuk membuat class yang mengandung logic setup yang bersifat reusable dan membuat functional test yang relevan untuk inherit dari class setup tersebut.
