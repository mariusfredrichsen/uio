# Web hacking 3, SQL injection, Xpath injection, Server side template injection, File inclusion

## Type of SQL injection exploitation
- Union query
    - combine a free query with a query that results in something
    - just append to the query
- Stacked query
    - if the database has stacked queries enabled you can write query 1; query 2; query 3;
- Time based blind
    - same as boolean based
- read local files
- writing local files
    - need: union select or stacked queries, know (or guess) row number and types of chained queries, a writable folder in the webroot, know the folder
    - http://193.255.218.188/sql3.php?email=fakeemail' union select 'Imagine heres the attacking script' '0', '0', '0' into outfile '/var/www/temp/aphpfile.php'
- executing OS commands
- sqli filter evasion
    - white space or 'a' = 'a'
    - Null bytes %00' UNION SELECT ... --
    - SQL comments 
        - '/**/UNION/\*\*/SELECT/\*\*'
    - URL encoding
    - Character encoding, char(114, 111, 111, 116)--
    - String concatination, EXEC('SEL' + 'ECT 1')
    - Hex encoding, unhex('726F6F74')

### SQL map
- --dbs
- -D selecteddb --tables
- -D selecteddb -T selectedtable --columns (or --dump)

## Xpath injection
- find data stored as XML
- query through the semi-structured data

## LFI vulnurebility
- php://filter/convert.base64-encode/resource is enabled
- can encode files with base64 and you can decode it to see how the .php file looks like to maybe exploit it