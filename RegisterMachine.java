import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class RegisterMachine {
  ArrayList<Instruction> instructions;
  ArrayList<Integer> registers;
  
  public RegisterMachine(String filePath) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(filePath));
    instructions = new ArrayList<Instruction>();
    String s = "";
    Instruction i;
    int largestRegister = 0;
    s = br.readLine();
    while (!(s == null)) {
      i = new Instruction(s, this);
      if (i.register > largestRegister) {
        largestRegister = i.register;
      }
      instructions.add(i);
      registers = new ArrayList<Integer>(Collections.nCopies(largestRegister+1, 0));
      s = br.readLine();
    }
  }
  
  public void configure(int... args) {
    for (int i = 0; i < args.length; i++) {
      registers.set(i+1, args[i]);
    }
  }
  
  public void execute() {
    int nextInstruction = 0;
    Instruction i;
    while(true) {
      i = instructions.get(nextInstruction);
      nextInstruction = i.execute();
    }
  }
  
  public void incrementRegister(int i) {
    registers.set(i, registers.get(i) + 1);
  }
  
  public boolean decrementRegister(int i) {
    if (registers.get(i) > 0) {
      registers.set(i, registers.get(i) - 1);
      return true;
    }
    return false;
  }
  
  public void printResult() {
    System.out.println(registers.get(0));
  }
  
  public static void main(String[] args) throws Exception {
    RegisterMachine rm = new RegisterMachine("test.txt");
    rm.configure(3,5);
    rm.execute();
  }
}