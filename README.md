# Esercizio-3---Compilatori-2019
Data la seguente grammatica

(
 N = {Program, Stmt, Expr, Term},
 T = {ID, IF, THEN, RELOP, NUMBER, ;, ASSIGN},  
 S = Program
 P = {
          Program -> Program ; Stmt
                           |  Stmt
           Stmt -> IF Expr THEN Stmt
                     | ID ASSIGN Expr

          Expr ->  Term  RELOP Term
                    |   Term

          Term ->  ID
                     |  NUMBER
       }
)

Dove i tokens sono per lo più quelli definiti nella esercitazione 1 precedente.

Esempi di frasi corrette :

    a := 5
    if a > 1 then if x < y then z := x >= 3;   a := 5; b := a <= b


Costruire un parser top-down a discesa ricorsiva seguendo le indicazioni della sezione 4.4.1 del testo di riferimento (pag. 194).
Verificare prima se la grammatica può essere utilizzata per costruire un parser top-down. Nel caso non lo sia, applicare le trasformazioni necessarie senza alterare il linguaggio risultante (si veda il libro di testo).