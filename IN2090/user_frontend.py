import psycopg2

# Login details for database user
dbname = "mafredri" # Set in your UiO-username
user = "mafredri_priv" # Set in your UiO-username + _priv
pwd = "vah2Aighe6" # Set inn the password for the _priv-user you got in a mail

# Gather all connection info into one string
connection = \
    "host='dbpg-ifi-kurs03.uio.no' " + \
    "dbname='" + dbname + "' " + \
    "user='" + user + "' " + \
    "port='5432' " + \
    "password='" + pwd + "'"

def frontend():
    conn = psycopg2.connect(connection) # Create a connection
    ch = 0
    username = ""
    while (username == ""):
        print("-- USER FRONTEND --")
        print("Please choose an option:\n 1. Register\n 2. Login\n 3. Exit")
        ch = get_int_from_user("Option: ", True)

        if (ch == 1):
            register(conn) # Register a new user
        elif (ch == 2):
            username = login(conn) # Login with existing user
        elif (ch == 3):
            return # Exit program
    # Once logged in, can now search for products
    search(conn, username)

def register(conn):
    
    print(" -- REGISTER NEW USER --")
    # Get credentials for new user account
    username = input("Username: ")
    password = input("Password: ")
    name = input("Name: ")
    address = input("Address: ")

    cur = conn.cursor() # Create a cursor object that can be used for executing queries
    # We can use %s as a place holder for a value, and then pass a tuple of values to be substituted for
    # these place holders. The first placeholder is then substituted with the first element in the tuple,
    # and so on.
    # NOTE: NEVER store passwords in plain text for an actual application!!!
    cur.execute("INSERT INTO ws.users(name, username, password, address) VALUES (%s, %s, %s, %s);",
                (name, username, password, address))
    conn.commit()
    print("New user " + username + " added!")
                  

def login(conn):
    print(" -- LOGIN --")
    username = input("Username: ")
    password = input("Password: ")

    cur = conn.cursor()
    # If we do not use these placeholders, the program is susceptible to SQL injection attacks
    # More on this next lecture.
    cur.execute("SELECT username, name FROM ws.users WHERE username = %s AND password = %s;",
                (username, password))

    # To get the resuts from a SELECT-query, we can call fetchall() on the
    # cursor. This will make a list of lists, where each inner list represents one row
    rows = cur.fetchall() # Retrieve all restults into a list of tuples
    if (rows == []):
        # The query returned no results, thus the user-password pair does not exist in the DB
        print("Incorrect username or password.")
        return ""
    else:
        row = rows[0]
        print("Welcome", row[1]) # Print "Welcome <name>"
        return row[0] # Return username

def search(conn, username):

    print(" -- SEARCH --")
    name = input("Search: ")
    category = input("Category: ")

    # We will now construct the search query based on the user's input
    # For long queries, is is helpful to name the placeholders
    # This is done by placing the name of the place holder in parenthesis between the % and the s
    q = "SELECT p.pid, p.name, p.price, c.name AS category, p.description " + \
        "FROM ws.products AS p INNER JOIN ws.categories AS c USING (cid)" + \
        "WHERE p.name LIKE %(name)s"

    if (category != ""):
        q += " AND c.name = %(category)s"

    q += ";"

    cur = conn.cursor()
    # We can then give a map from placeholder name to value, like below
    cur.execute(q, {'name' : "%"+name+"%", 'category' : category})
    rows = cur.fetchall() # Retrieve all restults into a list of tuples

    if (rows == []):
        print("No results.")
        return

    print(" -- RESULTS --\n")

    for row in rows:

        print("=== " + row[1] + " ===\n" + \
              "Product ID: " + str(row[0]) + "\n" + \
              "Price: " + str(row[2]) + "\n" + \
              "Category: " + row[3])

        if (row[3] != "NULL"):
            print("Description: " + row[4])

        print("\n")

    order_products(conn, username)


def order_products(conn, username):
    return

def get_int_from_user(msg, needed):
    # Utility method that gets an int from the user with the first argument as message
    # Second argument is boolean, and if false allows user to not give input, and will then
    # return None
    while True:
        numStr = input(msg)
        if (numStr == "" and not needed):
            return None;
        try:
            return int(numStr)
        except:
            print("Please provide an integer or leave blank.");


if __name__ == "__main__":
    frontend()
