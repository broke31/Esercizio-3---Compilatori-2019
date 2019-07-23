# Esercizio-3---Compilatori-2019
Data la seguente grammatica


 N = {Program, Stmt, Expr, Term},<br> 
    T = {ID, IF, THEN, RELOP, NUMBER, ;, ASSIGN},  <br> <br>
    S = P <br>
            P       -> P ; Stmt <br>
            P       ->    Stmt <br>
            Stmt    -> IF Expr THEN Stmt <br>
            Stmt    -> ID ASSIGN Expr <br>
            Expr    -> Term  RELOP Term <br>
            Expr    -> Term <br>
            Term    -> ID <br>
            Term    -> NUMBER <br>
       


Dove i tokens sono per lo più quelli definiti nella esercitazione 1 precedente.

Esempi di frasi corrette :

    a := 5
    if a > 1 then if x < y then z := x >= 3;   a := 5; b := a <= b


Costruire un parser top-down a discesa ricorsiva seguendo le indicazioni della sezione 4.4.1 del testo di riferimento (pag. 194).
Verificare prima se la grammatica può essere utilizzata per costruire un parser top-down. Nel caso non lo sia, applicare le trasformazioni necessarie senza alterare il linguaggio risultante (si veda il libro di testo).

## Passo 1: Eliminazione dell'ambiguità
Genericamente una grammatica è ambigua quando è possibile ottenere più di un albero di derivazione.<br>
Esiste una frase che ammette 2 o più alberi di derivazioni <br>
La grammatica non risulta ambigua.
## Passo 2: Eliminazione della ricorsione a sinistra
Una grammatica è detta ricorsiva a sinistra se ha un non terminale  A per cui esiste una derivazione A =>+ Aa <br> I parser top-down non possono gestire grammatiche con ricorsione a sinistra.<br>
La grammatica è ricorsiva a sinistra. <br>
Dopo aver eliminato la ricorsione, la grammatica si presenta nel seguente modo:<br>
    P   -> S P'<br>
    P'  -> ; S P' |esp<br>
    P   -> S<br>
    S   -> IF E THEN S<br>
    S   -> ID ASSIGN E<br>
    E   -> T RELOP T<br>
    E   -> T<br>
    T   -> ID<br>
    T   -> NUMBER <br>
## Passo 3: Fattorizzazione a sinistra
La fattorizzazione sinistra è una trasformazione che si usa quando due produzioni alternative per un non terminale A non è chiara<br>
possiamo riscriverle in modo da differire tale scelta finché non avremo letto abbastanza simboli d'ingresso da poter prendere una decisione corretta<br>
La grammatica deve essere trasformata tramite una fattorizzazione sinistra<br>
Dopo averla fattorizzata, la grammatica risulta nel seguente modo: <br>
    P   -> S P'<br>
    P'  -> ; S P' |esp<br>
    P   -> S<br>
    S   -> IF E THEN S<br>
    S   -> ID ASSIGN E<br>
    E   -> T E' <br>
    E'  -> RELOP T <br>
    E'  -> eps <br>
    T   -> ID<br>
    T   -> NUMBER <br>
