# DS_1920_Gr20
Projekti ne lëndën “Siguria e të dhënave”

Qëllimi:

Qëllimi i këtij projekti është që të krijohet një aplikacion për teknikat klasike 
të enkriptimit dhe dekripritmit të përpunimit  të të  dhënave tekstuale.
Ky aplikacion demostrohet duke shkruar një program të thjeshtë i cili ofron disa sherbime tekstuale.

Permbajta :

Detyra1:Komanda Frequency

Kjo komand numron frekuencën e paraqitjes së simboleve  dhe ASCII bar-grafi qe permes # vizualizon 
perqinjdet e simboleve në tekstin tonë përmes programit te shkruar nga ne në command prompt ose git bash përmes argumenteve:
Nese shenojme “Pershendetje nga FIEK! Në ekran do të  shtypet:

<img width="183"  alt="frequency" src="https://user-images.githubusercontent.com/52915274/77795540-fdcc6b00-706d-11ea-8cbc-37871075057e.png">
Detyra2:Komanda morse-code

Morse Kodi është metod për enkodimin e informative përmes kombinimit të sinjaleve të shkurtra dhe të gjata-te njohura si “dots”  dhe “dashes”  per  tekst karaktere.Kjo metodë bëhet permes dy nënkomandave

Nënkomanda encode:

Nëse ne shënojmë “takohemi neser” ,programi këtë fjali do e kthej në morse-code përmes pikës dhe vijës dhe poashtu do të jete edhe opsioni audio siqë do të shtypet në ekran: 

<img width="263" alt="encode " src="https://user-images.githubusercontent.com/52915274/77795960-bd212180-706e-11ea-9d46-7eb72cb25b9d.png">

Nënkomanda decode 

Nëse endodimin e fjalisë së kthyer në morse-code e kthejmë në alfabetin latin athërë bëhet me komandën decode si do të e shohim që shtyper në ekran: 

<img width="399" alt="decode" src="https://user-images.githubusercontent.com/52915274/77795952-b8f50400-706e-11ea-8413-48de561181e1.png">

Detyra3:Komanda four-square 

Kjo metodë cipher transformon plaintekstin përmes 4 tabelave 5x5 dhe disa rregullave.
Secila matrice përmaban shkronjat me dy tabela të cilat  jonë normale, dy të tjera të ndryshuara rregulla e kësaj metode është se kombinohet j dhe I për shkak të matricës 5x5.

Nënkomanda encrypt

Bëhet përmes ndarëjes dyfishe të shkronjave ku plaintextin tonë e kthen ne cipher kod të kombinuar përmes dy çelësave të cilët ne i përcaktojmë.
Nese shënojmë “takohemi neser” në  ekran do të shfaqet: 

<img width="347" alt="encrypt" src="https://user-images.githubusercontent.com/52915274/77795966-beeae500-706e-11ea-936e-3037f3fd5a1e.png">
Nënkomanda decrypt 

Dekripton ciphertextin në plaintext me dy çelsat tonë përmes four-square cipher dhe do të shtyp plaintextin në ekran: 

<img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915274/77795958-bb575e00-706e-11ea-87dd-816fbae84569.png">

-------------------------------------------------------FAZA E DYTE-------------------------------------------------

Komanda1:create-user

Krijon një çift të publik/privat të RSA me emrat <name>.xml dhe <name>.pub.xml brenda
direktoriumit të çelësave keys.
Direktoriumi i çelësave keys është folder që i mban çelësat publik dhe privat.


<img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81133981-8f73a600-8f53-11ea-849e-0bef9bc75fc3.PNG">

Komanda2:delete-user

I largon të gjithë çelësat ekzistues të shfrytëzuesit.
Sintaksa: ds delete-user <name>
  
  <img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81134591-8683d400-8f55-11ea-98d4-633ba01fecc1.PNG">

Komanda3:export-key

Eksporton çelësin publik ose privat të shfrytëzuesit nga direktoriumi i çelësave.

Sintaksa: ds export-key <public|private> <name> [file] 
  
Argumenti <public|private> e përcakton llojin e çelësit që eksportohet.

Argumenti <name> e përcakton çelësin e cilit shfrytëzues të eksportohet.
  
Argumenti opsional [file] e përcakton shtegun e fajllit se ku do të ruhet çelësi i eksportuar. Nëse
mungon argumenti atëherë çelësi do të shfaqet në console.
  
  
<img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81134707-dc587c00-8f55-11ea-8eb4-acafe06c31c2.PNG">

Komanda4:import-key

Importon çelësin publik ose privat të shfrytëzuesit nga shtegu i dhënë dhe e vendos në direktoriumin
e çelësave.

Sintaksa: ds import-key <name> <path>  
  
Argumenti <name> e përcakton emrin e çelësit që do të ruhet në direktoriumin keys.
  
Argumenti <path> e përcakton shtegun e çelësit që do të importohet.
  
  <img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81134852-5426a680-8f56-11ea-983e-8036407a0d58.PNG">
  
  Komanda5:write-message
  
E shkruan një mesazh të enkriptuar të dedikuar për një shfrytëzues.

Sintaksa: ds write-message <name> <message> [file]
  
Argumenti <name> e paraqet marrësin e mesazhit (çelësin publik).
  
Argumenti <message> e paraqet mesazhin që do të enkriptohet.
  
Argumenti opsional [file] e përcakton shtegun e fajllit se ku do të ruhet mesazhi i enkriptuar.

 <img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81138595-d5843600-8f62-11ea-927d-ea54a0e937c4.PNG">
 
 Komanda6:read-message
 
E dekripton dhe e shfaq në console mesazhin e enkriptuar.

Sintaksa: ds read-message <encrypted-message>
  
Argumenti<encrypted-message>paraqetmesazhin e enkriptuar sipas skemës së komandëswrite
-message.
  
  <img width="338" alt="decrypt" src="https://user-images.githubusercontent.com/52915332/81138649-095f5b80-8f63-11ea-9e6e-38794ae90d8a.PNG">
  
  -------------------------------------------------------FAZA E TRETE-------------------------------------------------
  
  
  Ne menyre qe te kompajlohet programi duhet se pari te perfshihen ne classpath dy jar files, njera qe sherben per te konektuar programin me databaze dhe tjetra per t'i shfrytezuar metodat per enkriptimin e passwordit ne formen e kerkuar. 
  
  Menyra e kompajlimit: javac Program.java.
  
  Zgjerimi i komandes "create-user":

Tani kjo komande perveq qe krijon celesin publik gjegjesisht ate privat kerkon nga user-i edhe nje fjalekalim ne menyre qe te behet identifikimi i drejte i userit kur deshiron me login.

Shenimin i fjalekalimit mund te behet ne dy menyra, me echo të simboleve në ekran, siq verejme me poshte:

<img width="660" alt="s" src="https://user-images.githubusercontent.com/52915274/83977207-fb548000-a8fe-11ea-9565-dddce03ac7ec.png">

apo pa echo te simboleve:

<img width="509" alt="s" src="https://user-images.githubusercontent.com/52915274/83977240-4078b200-a8ff-11ea-917e-12e23cf6f945.png">

Nese fjalekalimi nuk eshte gjashte ose me shume karaktere apo nuk permban numra apo karaktere tjera speciale atehere celesat nuk gjenerohen derisa fjalekalimi te jete valid.

<img width="552" alt="s" src="https://user-images.githubusercontent.com/52915274/83977290-c7c62580-a8ff-11ea-9bf7-c5feaf7890e3.png">

Zgjerimi i komandes "delete-user":

Emri dhe fjalekalimi i perdorueseve ruhen ne databaze dhe kur thirret kjo komande atehere fshihen celesi privat perkateshisht ai publik si dhe te dhenat e perdoruesit ne fjale ne tabelen e perdorueseve ne databaze.

<img width="433" alt="s" src="https://user-images.githubusercontent.com/52915274/83977363-5e92e200-a900-11ea-895a-2972755927d7.png">


Komanda login:

Kjo komande teston çiftin shfrytëzues/fjalëkalim. Në rast suksesi lëshohet një token i nënshkruar i cili mund të përdoret për autentikim të shfrytëzuesit. Tokeni perbehet nga dy pjese, ne pjesen e pare jane te vendosura emri dhe koha kur skadon tokeni dhe ne pjesen e dyte eshte i vendosur nenshkrimi i tokenit.

<img width="664" alt="s" src="https://user-images.githubusercontent.com/52915274/83977472-0c9e8c00-a901-11ea-9ff0-346650773f01.png">

Si dhe pa echo te karaktereve ne ekran:

<img width="659" alt="s" src="https://user-images.githubusercontent.com/52915274/83977511-41124800-a901-11ea-93b0-da8cacdb75e5.png">

Komanda status:

Jep informata rreth tokenit

<img width="655" alt="s" src="https://user-images.githubusercontent.com/52915274/83977555-a1a18500-a901-11ea-947f-8763b5ff27bb.png">

nese tokeni nuk eshte valid:

<img width="656" alt="s" src="https://user-images.githubusercontent.com/52915274/83979699-a1f54c80-a910-11ea-853d-0f51edfc2796.png">




Zgjerimi i komandes "write-message"

Nese si argumente jepen perveq argumenteve te zakonshme edhe --sender <token> atehere ciphertext do te zgjerohet me emrin e shfrytezuesit qe i takon tokeni ne utf-8 pastaj te enkoduar ne Base64 si dhe nenshkrimin me celesin privat te sender.
Nese tokeni nuk eshte valid atehere pjesa e fundit nuk do te merret parasysh.
  
  <img width="949" alt="s" src="https://user-images.githubusercontent.com/52915274/83977694-c9ddb380-a902-11ea-90da-3247a91858ec.png">
  
  Nese tokeni nuk eshte valid:
  
  <img width="661" alt="s" src="https://user-images.githubusercontent.com/52915274/83979754-f7c9f480-a910-11ea-876d-2921fff53822.png">
  
  
 Komanda e zgjeruar "read-message":

Nese ne ciphertextin e derguar figuron pjesa e derguesit dhe nenshkrimit atëherë do të tentohet verifikimi i atij nënshkrimi duke përdorur çelësin publik të dërguesit.

<img width="947" alt="s" src="https://user-images.githubusercontent.com/52915274/83977755-4b354600-a903-11ea-834f-fde736fbd512.png">

Nese nuk figuron pjesa e fundit atehere do te ekzekutohet si ne fazen e dyte.

Nese mungon celesi publik i sender atehere do te paraqitet si me poshte:

<img width="944" alt="s" src="https://user-images.githubusercontent.com/52915274/83977819-a1a28480-a903-11ea-9093-72884920282b.png">






 
  

