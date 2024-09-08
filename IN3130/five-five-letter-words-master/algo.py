from collections import defaultdict
from time import time


def convert_file(filename):
    # Each line in the file is a word
    # Ignore the words that are not 5 long or has a duplicat letter
    letter_words = defaultdict(set)
    words = set()

    with open(filename) as f:
        for word in f:
            word = word.strip()
            if len(word) == 5 and len(set(word)) == 5:
                words.add(word)
                for letter in word:
                    letter_words[letter].add(word)
    
    return letter_words, words

def main():
    letter_words, words = convert_file("input")
    five_words = set()
    count = 0

    for word1 in words:
        visited = set()
        for letter2 in letter_words:
            if letter2 in word1:
                continue
            for word2 in letter_words[letter2]:
                if word2 in visited:
                    continue
                if len(set(word1+word2)) != 10:
                    visited.add(word2)
                    continue

                for letter3 in letter_words:
                    if letter3 in word1 + word2:
                        continue
                    for word3 in letter_words[letter3]:
                        if word3 in visited:
                            continue
                        if len(set(word1+word2+word3)) != 15:
                            visited.add(word3)
                            continue

                        for letter4 in letter_words:
                            if letter4 in word1 + word2 + word3:
                                continue
                            for word4 in letter_words[letter4]:
                                if word4 in visited:
                                    continue
                                if len(set(word1+word2+word3+word4)) != 20:
                                    visited.add(word4)
                                    continue
                                
                                for letter5 in letter_words:
                                    if letter5 in word1 + word2 + word3 + word4:
                                        continue
                                    for word5 in letter_words[letter5]:
                                        if word5 in visited:
                                            continue
                                        if len(set(word1+word2+word3+word4+word5)) != 25:
                                            visited.add(word5)
                                            continue
                                        
                                        asd = " ".join([word1,word2,word3,word4,word5])
                                        print(asd)
                                        count += 1
                                        five_words.add(asd)



if __name__ == "__main__":
    main()