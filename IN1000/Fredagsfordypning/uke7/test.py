from lag import Lag



lag1 = Lag("SIF", 0.1, 0.2)
print(lag1.hent_navn(), lag1.hent_angrep(), lag1.hent_forsvar())