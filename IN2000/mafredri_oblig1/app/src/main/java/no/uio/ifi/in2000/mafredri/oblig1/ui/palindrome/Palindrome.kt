package no.uio.ifi.in2000.mafredri.oblig1.ui.palindrome

fun isPalindrome(palindrome: String) = palindrome.lowercase() == palindrome.lowercase().reversed()