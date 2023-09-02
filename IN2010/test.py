import csv

def read_csv(filnavn1, filnavn2, filnavn3):
    with open(filnavn1) as kurs:
        reader = csv.reader(kurs)
        kurs = []
        for row in reader:
            if row[0].strip()[0:2] == "IN":
                kurs.append([row[0], row[1]])
        print(kurs)
            
        with open(filnavn2) as tar_kurs:
            reader = csv.reader(tar_kurs)
            studentId = []
            for row in reader:
                if row[1].strip()[0:2] == "IN":
                    studentId.append(row[0])
            studentId = set(studentId)

            with open(filnavn3) as studenter:
                reader = csv.DictReader(studenter)
                gamleStudenter = []
                for row in reader:
                    for id in studentId:
                        if row["studeintid"] == id:
                            if row[" fÃ¸dt"].strip() < "1992-01-01":
                                gamleStudenter.append(row["studeintid"])

                with open(filnavn2) as tar_kurs:
                    reader = csv.DictReader(tar_kurs)
                    for row in reader:
                        for id in gamleStudenter:
                            if row["studeintid"] == id:
                                print(row)
                                
                            
                    



read_csv("kurs.csv", "tar_kurs.csv", "studenter.csv")
    