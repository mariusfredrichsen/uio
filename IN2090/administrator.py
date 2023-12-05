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
    print(" -- BILLS --")
    username = input("Username: ")
    cur = conn.cursor()
    if username:
        q = f"""SELECT u.name, u.address, sum(o.num*p.price)
                FROM users AS u
                INNER JOIN orders AS o USING (uid) 
                INNER JOIN products AS p USING (pid)
                WHERE u.username = '{username}' AND o.payed = 0
                GROUP BY u.name, u.address;"""
    else:
        q = f"""SELECT u.name, u.address, sum(o.num*p.price)
                FROM users AS u
                INNER JOIN orders AS o USING (uid) 
                INNER JOIN products AS p USING (pid)
                WHERE o.payed = 0
                GROUP BY u.name, u.address;"""
    cur.execute(q)
    rows = cur.fetchall()
    for row in rows:
        print(f"---Bill---\nName: {row[0]}\n Address: {row[1]}\n Total due: {row[2]}")
    if username:
        conn.commit()
    
    
    return # Oppg 3

def insert_product(conn):
    print("-- ADMINISTRATOR --")
    name = input("Product name: ")
    price = input("Price: ")
    category = input("Category: ")
    desc = input("Description: ")
    
    q = f"""INSERT INTO ws.products (name, price, cid, description)
            VALUES ('{name}', {price}, (SELECT cid FROM categories WHERE name = '{category}'), '{desc}');"""
    
    cur = conn.cursor()
    cur.execute(q)
    conn.commit()
    print("New product " + name + " inserted.")
    return # Oppg 4

if __name__ == "__main__":
    administrator()