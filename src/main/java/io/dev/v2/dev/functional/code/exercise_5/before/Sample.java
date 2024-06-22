package io.dev.v2.dev.functional.code.exercise_5.before;

class Applicant {
  public boolean isCredible() {
    return true;    
  }                            
  
  public int getCreditScore() {
    return 700;
  }
  
  public int getEmploymentYears() {
    return 10;
  }
  
  public boolean hasCriminalRecord() {
    return true;
  }
}                  
                                       
interface Evaluator {
  boolean evaluate(Applicant applicant);
} 

class QualifiedEvaluator implements Evaluator {
  public boolean evaluate(Applicant applicant) {
    return applicant.isCredible();
  }
}     

class EvaluatorChain implements Evaluator {
  private Evaluator next;
  
  public EvaluatorChain(Evaluator nextEvaluator) {
    next = nextEvaluator;
  }                      
  
  public boolean evaluate(Applicant applicant) {
    return next.evaluate(applicant);
  }
}

class CreditEvaluator extends EvaluatorChain {
  public CreditEvaluator(Evaluator next) {
    super(next);
  }             
  
  public boolean evaluate(Applicant applicant) {
    if(applicant.getCreditScore() > 600)
      return super.evaluate(applicant);
    return false;
  }
}

class EmploymentEvaluator extends EvaluatorChain {
  public EmploymentEvaluator(Evaluator next) {
    super(next);
  }             
  
  public boolean evaluate(Applicant applicant) {
    if(applicant.getEmploymentYears() > 0)
      return super.evaluate(applicant);
    return false;
  }
}

class CriminalRecordsEvaluator extends EvaluatorChain {
  public CriminalRecordsEvaluator(Evaluator next) {
    super(next);
  }             
  
  public boolean evaluate(Applicant applicant) {
    if(!applicant.hasCriminalRecord())
      return super.evaluate(applicant);
    return false;
  }
}

class Sample {          
  public static void evaluate(Applicant applicant, Evaluator evaluator) {
    String result = evaluator.evaluate(applicant) ? "accepted" : "rejected";
    System.out.println("Result of evaluating applicant: " + result);
  } 
  
  public static void main(String[] args) {
    Applicant applicant = new Applicant();
    evaluate(applicant, new CreditEvaluator(new QualifiedEvaluator()));
    
    evaluate(applicant, 
      new CreditEvaluator(new EmploymentEvaluator(new QualifiedEvaluator())));

    evaluate(applicant, 
      new CriminalRecordsEvaluator(
      new EmploymentEvaluator(new QualifiedEvaluator())));

    evaluate(applicant, 
      new CriminalRecordsEvaluator(
      new CreditEvaluator(
      new EmploymentEvaluator(new QualifiedEvaluator()))));
  }              
}
