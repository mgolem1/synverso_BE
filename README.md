# synverso project

Synverso project se sastoji od tri modula:
Projekt se dijeli na tri modula, i to synverso-repo, synverso-services i synverso-web. Synverso repo
modul, sadrži model aplikacije, repozitorije, koji se koristi za CRUD operacije. Synverso
services se nalazi servisi, sadrži poslovnu logiku i izvršava operacije. Synverso web se nalazi
kontroler, prima ulaz od strane korisnika i obrađuje podatke pomoću sloja modela te ih
vraća. Moduli komuniciraju tako da synverso-web može dohvatiti sve iz modula synverso-services,
ali ne iz synverso-repo, dok synverso-services može dohvatiti iz synverso-repo, ali ne iz synverso-web, a
synverso-repo je zaseban ne može dohvatiti iz niti jednog modula. To se ostvaruje na način da u
datoteci pom.xml navedemo module koje imamo, te u svakom zasebnom modulu u
datoteci pom.xml navedemo ovisnost (eng. dependecy) modula kojeg želimo da koristimo. 

Unutar controllera imamo 4 osnova endpointa za osnove CRUD operacije, kreiranje, ažuriranje, brisanje i dohvat. 