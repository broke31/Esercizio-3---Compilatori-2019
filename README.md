# Esercizio-3---Compilatori-2019
Data la seguente grammatica

 ```flex
 N = {Program, Stmt, Expr, Term},
    T = {ID, IF, THEN, RELOP, NUMBER, ;, ASSIGN},
    S = P 

            P-> P;Stmt 
            P->Stmt
            Stmt->IF Expr THEN Stmt 
            Stmt->ID ASSIGN Expr 
            Expr->Term  RELOP Term
            Expr->Term 
            Term->ID 
            Term->NUMBER
```
       


Dove i tokens sono per lo più quelli definiti nella esercitazione 1 precedente.

Esempi di frasi corrette :

    a := 5
    if a > 1 then if x < y then z <-- x >= 3;   a <-- 5; b <-- a <= b


Costruire un parser top-down a discesa ricorsiva seguendo le indicazioni della sezione 4.4.1 del testo di riferimento (pag. 194).
Verificare prima se la grammatica può essere utilizzata per costruire un parser top-down. Nel caso non lo sia, applicare le trasformazioni necessarie senza alterare il linguaggio risultante (si veda il libro di testo).

## Passo 1: Eliminazione dell'ambiguità
Genericamente una grammatica è ambigua quando è possibile ottenere più di un albero di derivazione.<br>
Esiste una frase che ammette 2 o più alberi di derivazioni <br>
La grammatica non risulta ambigua.
## Passo 2: Eliminazione della ricorsione a sinistra
Una grammatica è detta ricorsiva a sinistra se ha un non terminale  A per cui esiste una derivazione A =>+ Aa <br> I parser top-down non possono gestire grammatiche con ricorsione a sinistra.<br>
La grammatica è ricorsiva a sinistra. <br>
Dopo aver eliminato la ricorsione, la grammatica si presenta nel seguente modo:
```flex
    P->S P'
    P'->;SP'|esp
    S->IF E THEN S
    S->ID ASSIGN E
    E->T RELOP T
    E->T
    T->ID
    T->NUMBER
 ```
## Passo 3: Fattorizzazione a sinistra
La fattorizzazione sinistra è una trasformazione che si usa quando due produzioni alternative per un non terminale A non è chiara<br>
possiamo riscriverle in modo da differire tale scelta finché non avremo letto abbastanza simboli d'ingresso da poter prendere una decisione corretta<br>
La grammatica deve essere trasformata tramite una fattorizzazione sinistra<br>
Dopo averla fattorizzata, la grammatica risulta nel seguente modo:<br>
```flex
    P->SP'
    P'->;SP'|esp
    S->IF E THEN S
    S->ID ASSIGN E
    E->TE'
    E'->RELOP T
    E'-> eps
    T->ID
    T->NUMBER
```
