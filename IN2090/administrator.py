import psycopg2

# MERK: Må kjøres med Python 3

user = 'mafredri_priv' # Sett inn ditt UiO-brukernavn ("_priv" blir lagt til under)
pwd = 'vah2Aighe6' # Sett inn passordet for _priv-brukeren du fikk i en mail

connection = \
    "dbname='" + user + "' " +  \
    "user='" + user + "_priv' " + \
    "port='5432' " +  \
    "host='dbpg-ifi-kurs03.uio.no' " + \
    "password='" + pwd + "'"

def administrator():
    conn = psycopg2.connect(connection)
    
    ch = 0
    while (ch != 3):
        print("-- ADMINISTRATOR --")
        print("Please choose an option:\n 1. Create bills\n 2. Insert new product\n 3. Exit")
        ch = int(input("Option: "))

        if (ch == 1):
            make_bills(conn)
        elif (ch == 2):
            insert_product(conn)
    
def make_bills(conn):
    
    return # Oppg 3

def insert_product(conn):
    return # Oppg 4

if __name__ == "__main__":
    administrator()
