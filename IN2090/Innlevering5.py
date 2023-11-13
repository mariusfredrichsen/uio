import psycopg2

# MERK: Må kjøres med Python 3

user = 'mafredri' # Sett inn ditt UiO-brukernavn ("_priv" blir lagt til under)
pwd = 'vah2Aighe6' # Sett inn passordet for _priv-brukeren du fikk i en mail

connection = \
    "dbname='" + user + "' " +  \
    "user='" + user + "_priv' " + \
    "port='5432' " +  \
    "host='dbpg-ifi-kurs03.uio.no' " + \
    "password='" + pwd + "'"

def huffsa():
    conn = psycopg2.connect(connection)
    
    ch = 0
    while (ch != 3):
        print("--[ HUFFSA ]--")
        print("Vennligst velg et alternativ:\n 1. Søk etter planet\n 2. Legg inn forsøksresultat\n 3. Avslutt")
        ch = int(input("Valg: "))

        if (ch == 1):
            planet_sok(conn)
        elif (ch == 2):
            legg_inn_resultat(conn)
    
def planet_sok(conn):
    print("--[ PLANET-SØK ]--")
    molekyl1 = input("Molekyl 1: ")
    molekyl2 = input("Molekyl 2: ")
    
    cur = conn.cursor()
    if molekyl1:
        if molekyl2:
            q = f"""SELECT p.navn, p.masse, s.navn, s.masse, s.avstand, p.liv
                    FROM planet AS p
                    FULL JOIN materie AS m1 ON (m1.planet = p.navn)
                    FULL JOIN materie AS m2 ON (m2.planet = p.navn AND m1.molekyl != m2.molekyl)
                    FULL JOIN stjerne AS s ON (s.navn = p.stjerne)
                    WHERE m1.molekyl = '{molekyl1}' AND m2.molekyl = '{molekyl2}';
                """
        else:
            q = f"""SELECT p.navn, p.masse, s.navn, s.masse, s.avstand, p.liv
                    FROM planet AS p
                    FULL JOIN materie AS m1 ON (m1.planet = p.navn)
                    FULL JOIN stjerne AS s ON (s.navn = p.stjerne)
                    WHERE m1.molekyl = '{molekyl1}';
                """
    cur.execute(q)
    rows = cur.fetchall()
    
    for row in rows:
        print(f"""--Planet--
Navn: {row[0]}
Planet-masse: {row[1]}
Stjerne-masse: {row[2]}
Stjerne-distanse: {row[3]}""")
        if row[4]:
            if row[4] == 'j':
                print("Bekreftet liv: Ja")
            else:
                print("Bekreftet liv: Nei")
        print()
    
    conn.commit()

def legg_inn_resultat(conn):
    planet = input("Planet: ")
    skummel = input("Skummel: ")
    intelligent = input("Intelligent: ")
    beskrivelse = input("Beskrivelse: ")
    
    if skummel == 'j':
        skummel = 't'
    else:
        skummel = 'f'
    
    if intelligent == 'j':
        intelligent = 't'
    else:
        intelligent = 'f'
    
    q = f"UPDATE inv4.planet SET skummel = '{skummel}', intelligent = '{intelligent}', beskrivelse = '{beskrivelse}' WHERE navn = '{planet}';"
    cur = conn.cursor()
    cur.execute(q)
    
    conn.commit()
    
    print("Resultat lagt inn.")

if __name__ == "__main__":
    huffsa()
