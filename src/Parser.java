public class Parser {
  LexicalAnalyzer lexicalAnalyzer;
  public Token next;
  String lessema;

  public Parser() throws Exception {
    lexicalAnalyzer = new LexicalAnalyzer();
    next = lexicalAnalyzer.nextToken();
    lessema = "";
  }

  public boolean P() throws Exception {
    return S() && PFirst();
  }

  private boolean S() throws Exception {
    lessema = next.getName();
    if (lessema.equals("IF")) {
      next = lexicalAnalyzer.nextToken();
      lessema = next.getName();
      if (E()) {
        if (lessema.equals("THEN")) {
          next = lexicalAnalyzer.nextToken();
          lessema = next.getName();
          return S();
        }
      }
    } else if (lessema.equals("ID")) {
      next = lexicalAnalyzer.nextToken();
      lessema = next.getName();
      if (lessema.equals("ASSIGN")) {
        next = lexicalAnalyzer.nextToken();
        lessema = next.getName();
        return E();
      }
    }
    return false;
  }

  private boolean E() throws Exception {
    return T() && EFirst();
  }

  private boolean EFirst() throws Exception {
    if (lessema.equals("relop")) {
      next = lexicalAnalyzer.nextToken();
      lessema = next.getName();
      return T();
    }

    return true; // eps
  }

  private boolean T() {
    /*
    boolean app = lessema.equals("ID") || lessema.equals("NUMBER");
    try {
      next = lexicalAnalyzer.nextToken();
      lessema = next.getName();
    } catch (Exception e) {
      return true;
    }
    return app;
    */
    if(lessema.equals("ID") || lessema.equals("NUMBER")) {
      try {
        next = lexicalAnalyzer.nextToken();
        lessema = next.getName();
        return true;
      }catch (Exception e) {
        return true;
      }
    }
    return false;
  }

  private boolean PFirst() {
    if (next == null) {
      return true;
    }
    lessema = next.getAttribute();
    if (lessema != null) {
      if (lessema.equalsIgnoreCase(";")) {
        try {
          next = lexicalAnalyzer.nextToken();
        } catch (Exception e) {
          return false;
        }
        try {
          if (S() && PFirst()) {
            return true;
          }
        } catch (Exception e) {
          return false; // ho trovato un ; ma non ho trovato nulla dopo... e la stringa non può terminare con un ;
        }
      }
    }
    return true;
  }
}
