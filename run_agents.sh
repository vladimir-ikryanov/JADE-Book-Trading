#!/bin/bash
javac -cp lib/jade.jar src/ua/nure/BookBuyerAgent.java src/ua/nure/BookSellerDialog.java src/ua/nure/BookSellerAgent.java
java -cp lib/jade.jar:src/.:. jade.Boot -agents seller:ua.nure.BookSellerAgent\;buyer:ua.nure.BookBuyerAgent\(Cinderella\)