public class Instruction {
  RegisterMachine m;
  final Type t;
  final int register;
  final int jumpIfSuccess;
  final int jumpIfFail;
  
  public Instruction(String s, RegisterMachine m) {
    System.out.println(s);
    this.m = m;
    if ((s.length() < 1)) {
      throw new IllegalArgumentException("Should be > 1 chars.");
    }
    switch (s.charAt(0)) {
      case '+':
        t = Instruction.Type.INC;
        break;
        
      case '-':
        t = Instruction.Type.DEC;
        break;
        
      case 'H':
        t = Instruction.Type.HALT;
        break;
        
      default:
        throw new IllegalArgumentException("Must start with +, -, or H.");
    }
    
    s = s.substring(1, s.length());
    String[] values = s.split(",");
    if (!(this.t == Instruction.Type.HALT)) {
      register = Integer.parseInt(values[0]);
      this.jumpIfSuccess = Integer.parseInt(values[1]);
    } else {
      this.register = -1;
      this.jumpIfSuccess = -1;
    }
    if (this.t == Instruction.Type.DEC) {
      jumpIfFail = Integer.parseInt(values[2]);
    } else {
      jumpIfFail = -1;
    }
  }
  
  public int execute() {
    if (this.t == Instruction.Type.DEC) {
      if (m.decrementRegister(register)) {
        return jumpIfSuccess;
      }
      return jumpIfFail;
    } else if (this.t == Instruction.Type.INC) {
      m.incrementRegister(register);
      return jumpIfSuccess;
    } else {
      m.printResult();
      System.exit(0);
      return 0;
    }
  }
  
  public enum Type {
    INC,
    DEC,
    HALT;
  }
}