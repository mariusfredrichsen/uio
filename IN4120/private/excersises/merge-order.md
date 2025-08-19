| Term         | Frequency |
|--------------|-----------|
| eyes         | 213312    |
| kaleidoscope | 87009     |
| marmalade    | 107913    |
| skies        | 271658    |
| tangerine    | 46653     |
| trees        | 316812    |

### Recommend a query processing order for
(**_tangerine_** OR **_trees_**) AND <br>
(**_marmalade_** OR **_skies_**) AND <br>
(**_kaleidoscope_** OR **_eyes_**) <br>

Suggested solution: <br>
tangerine + trees = 46653 + 316812 = **363465**  
marmalade + skies = 107913 + 271658 = **379571**  
kaleidoscope + eyes = 87009 + 213312 = **300321**  

((kaleidoscope OR eyes) AND (tangerine OR trees)) AND (marmalade OR skies)

