Feature: User booking hotel with login
  Scenario: User Login with valid data
    Given User open website
    When Maximize browser
    And User klik Element Sign In
    And User input email
    And User input password
    And User click button Sign In
    Then User masuk kehalaman akun


    Scenario: User memilih hotel
      Given User open website
      When Maximize browser
      And User klik Element Sign In
      And User input email
      And User input password
      And User click button Sign In
      Then User masuk kehalaman akun
      When User kembali kehalaman awal
      And User mengisi Location Hotel
      And User memilih Select Hotel
      And User memilih Date Check In
      And User memilih Date Check Out
      And User meng-klik button Search Now
      Then Muncul list pilihan kamar

      Scenario: User memilih kamar hotel
        Given User open website
        When Maximize browser
        And User klik Element Sign In
        And User input email
        And User input password
        And User click button Sign In
        Then User masuk kehalaman akun
        When User kembali kehalaman awal
        And User mengisi Location Hotel
        And User memilih Select Hotel
        And User memilih Date Check In
        And User memilih Date Check Out
        And User meng-klik button Search Now
        Then Muncul list pilihan kamar
        When User memilih kamar Luxury Room
        And Menampilkan details kamar
        And User melakukan pemesanan kamar
        Then Muncul halaman popup pesanan masuk cart
        When User klik button Proceed to checkout
        Then Menampilkan halaman pengisian alamat

      Scenario: User melakukan pengisian alamat
        Given User open website
        When Maximize browser
        And User klik Element Sign In
        And User input email
        And User input password
        And User click button Sign In
        Then User masuk kehalaman akun
        When User kembali kehalaman awal
        And User mengisi Location Hotel
        And User memilih Select Hotel
        And User memilih Date Check In
        And User memilih Date Check Out
        And User meng-klik button Search Now
        Then Muncul list pilihan kamar
        When User memilih kamar Luxury Room
        And Menampilkan details kamar
        And User melakukan pemesanan kamar
        Then Muncul halaman popup pesanan masuk cart
        When User klik button Proceed to checkout
        Then Menampilkan halaman pengisian alamat
        When User mengisi First Name
        And User mengisi Last Name
        And User mengisi Address
        And User mengisi Postal Code
        And User mengisi City
        And User mengisi State
        And User mengisi Country
        And User mengisi Mobile Phone
        And User mengisi Alias Address
        And User klik button Save

