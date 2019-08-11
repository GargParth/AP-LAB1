import java.util.*;


class Application
{
    //this class is used to store the details of all applications that a students
    // makes
    String CompanyName;
    Float MarksInTechnicalRound;

    Application(String cname, Float marks)
    {
        this.CompanyName = cname;
        this.MarksInTechnicalRound = marks;
    }

    @Override
    public String toString()
    {
        return (this.CompanyName +" "+ String.valueOf(this.MarksInTechnicalRound)); 
    }
}

class Student
{
    //This class stores all the details of the students
    //It does not do any kind of computation
    //It only stores data


    private static int LastRollNumber; //stores the last roll number assigned to any student
    private final int RollNumber; //stores the roll number assigned to this student
    private float CGPA; //CGPA of this student
    private Boolean PlacementStatus; //is the student placed or not 
    private String Branch;
    private String CompanyPlaced;
    private ArrayList<Application> studentApplications;


    //Getters ans setters beggining

    /**
     * @return the companyPlaced
     */
    public String getCompanyPlaced() {
        return CompanyPlaced;
    }

    /**
     * @return the lastRollNumber
     */
    public static int getLastRollNumber() {
        return LastRollNumber;
    }

    /**
     * @return the rollNumber
     */
    public int getRollNumber() {
        return RollNumber;
    }

    /**
     * @return the cGPA
     */
    public float getCGPA() {
        return CGPA;
    }

    /**
     * @return the placementStatus
     */
    public Boolean getPlacementStatus() {
        return PlacementStatus;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
        return Branch;
    }

    /**
     * @param placementStatus the placementStatus to set
     */
    public void setPlacementStatus(Boolean placementStatus) {
        this.PlacementStatus = placementStatus;
    }

    /**
     * @param companyPlaced the companyPlaced to set
     */
    public void setCompanyPlaced(String companyPlaced) {
        CompanyPlaced = companyPlaced;
    }


    public void ApplyToCompany(String cname, float marks_scored) {
        // this function creates a new application for the student and adds it to the
        // portfolio of companies that the studet applies in

        Application application = new Application(cname, marks_scored);
        this.studentApplications.add(application);
    }

    

    //Class constructor 

    Student(Float cgpa, String branch)
    {
        this.RollNumber = getLastRollNumber() + 1;
        LastRollNumber += 1;
        this.CGPA = cgpa;
        this.Branch = branch;   
        this.PlacementStatus = false;
        this.CompanyPlaced = "";
        this.studentApplications = new ArrayList<Application>();
    }

    //Class Functions beggining here
    public void DisplayStudent()
    {
        System.out.println();
        System.out.println("Roll Number : "+this.RollNumber);
        System.out.println("CGPA : "+this.CGPA);
        System.out.println("Course : "+this.Branch);
        if(this.PlacementStatus == false) System.out.println("Placement Status : Not Placed");
        else
        {
            System.out.println("Placement Status : Placed in "+this.getCompanyPlaced());
        }
        System.out.println();
    }

    public void DisplayApplications()
    {
        System.out.println();
        for(Application a : studentApplications)
        {
            System.out.println(a);
        }
        System.out.println();
    }

}


class TechnicalRound implements Comparable<TechnicalRound>
{
    //This class is used to Store the records for the
    //Technical round of any company

    Student data;
    Float TechnicalRoundMarks;
    Boolean selected;

    TechnicalRound(Student stu, Float marks)
    {
        this.data = stu;
        this.TechnicalRoundMarks = marks;
        this.selected = false;
    }


    //implementing the comparable
    @Override
    public int compareTo(TechnicalRound ob2)
    {
        if (this.TechnicalRoundMarks != ob2.TechnicalRoundMarks)
        {
            if(ob2.TechnicalRoundMarks - this.TechnicalRoundMarks > 0)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
            if(this.data.getCGPA()-ob2.data.getCGPA()>0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
    }
}


class Company
{
    //This class stores all kinds of details of any company
    //It does not do any kind of computation
    //It is only used to store data

    private final String CompanyName;
    private int StudentsRequired; //The number of students that the company will recruit
    private ArrayList<String> CourseCriteria; //The list of course criteria that a company accepts
    private Boolean ApplicationStatus;
    private ArrayList<TechnicalRound> EligibleStudents;

    //getters ans setters beggining
    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * @return the courseCriteria
     */
    public ArrayList<String> getCourseCriteria() {
        return CourseCriteria;
    }
    /**
     * @return the eligibleStudents
     */
    public ArrayList<TechnicalRound> getEligibleStudents() {
        return EligibleStudents;
    }

    /**
     * @return the studentsRequired
     */
    public int getStudentsRequired() {
        return StudentsRequired;
    }
    
    /**
     * @return the applicationStatus
     */
    public Boolean getApplicationStatus() {
        return ApplicationStatus;
    }

    /**
     * @param applicationStatus the applicationStatus to set
     */
    public void setApplicationStatus(Boolean applicationStatus) {
        ApplicationStatus = applicationStatus;
    }

    /**
     * @param eligibleStudents the eligibleStudents to set
     */
    public void setEligibleStudents(ArrayList<TechnicalRound> eligibleStudents) {
        EligibleStudents = eligibleStudents;
    }

    
    //Constructor for the class
    Company(String cname, ArrayList<String> courseCriteria, int student_requirement, ArrayList<TechnicalRound> scores)
    {
        this.CompanyName = cname;
        this.CourseCriteria = courseCriteria;
        this.StudentsRequired = student_requirement;
        this.ApplicationStatus = true;
        this.EligibleStudents = scores;
        Collections.sort(EligibleStudents);
    }

    //Creating class functions ahead

    public void DisplayCompany()
    {
        //This function displayes all the details of the 
        //company as required

        System.out.println();
        System.out.println("Company Name : "+this.getCompanyName());
        System.out.println("Course Criteria : ");
        //loop to iterate over all the accepted branches for this company
        for(String branchaccepted : CourseCriteria )
            System.out.println(branchaccepted);
        System.out.println("Number of required students : "+ this.getStudentsRequired());
        if(this.ApplicationStatus == true)
        {
            System.out.println("Application Status : OPEN");
        }
        else
        {
            System.out.println("Application Status : CLOSED");
        }   
    }

    public void SelectStudents()
    {
        // This method allows the company to select the number of required people

        // For a student  to be selected, he must be in the company's 
        // EligibleStudents List and should not be placed in any other 
        // Company, Also the company must have a vacancy for him


        for (TechnicalRound Applicant: EligibleStudents)
        {
            System.out.println();
            if(this.StudentsRequired>0)
            {
                //This means that the company has a vacancy

                if(Applicant.selected == false && Applicant.data.getPlacementStatus() == false)
                {
                    //The applicant is open for placements
                    //He is not placed until now

                    this.StudentsRequired -= 1;
                    Applicant.selected = true;
                    //Applicant.data.ApplyToCompany(this.CompanyName, Applicant.TechnicalRoundMarks);
                    Applicant.data.setPlacementStatus(true);
                    Applicant.data.setCompanyPlaced(this.getCompanyName());
                    System.out.println(Applicant.data.getRollNumber());
                    if(this.StudentsRequired == 0)
                    {
                        //Close the application process of the company
                        this.setApplicationStatus(false);
                        break;
                    }
                }
            }
        }
    }
}

class PlacementOffice
{
    //This will act as the main class

    private ArrayList<Student> RegisteredStudents;
    private ArrayList<Company> RegisteredCompanies;

    PlacementOffice()
    {
        this.RegisteredCompanies = new ArrayList<Company>();
        this.RegisteredStudents = new ArrayList<Student>();
    }

    
    //defining getters and setters
    
    /**
     * @return the registeredCompanies
     */
    public ArrayList<Company> getRegisteredCompanies() {
        return RegisteredCompanies;
    }

    /**
     * @return the registeredStudents
     */
    public ArrayList<Student> getRegisteredStudents() {
        return RegisteredStudents;
    }


    public void Register_Students(int NStudents)
    {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<NStudents; i++)
        {
            String data = sc.nextLine();
            String[] broken = data.split(" ");
            Float GPA = Float.parseFloat(broken[0]);
            String branch = broken[1];

            Student newStu = new Student(GPA, branch);
            RegisteredStudents.add(newStu);
        }
        //sc.close();
        System.out.println("---------- Students Registered ----------");
    }

    public void Register_company()
    {
        Scanner sc = new Scanner(System.in);
        String Cname = sc.nextLine().toUpperCase();
        ArrayList<String> coursecriteria = new ArrayList<String>();
        System.out.print("Number of Eligible Courses : ");
        int n = Integer.parseInt(sc.nextLine());
        for(int i=0; i < n; i++)
        {
            coursecriteria.add(sc.nextLine().toUpperCase());
        }
        System.out.print("Number of Required Students : ");
        int student_requirement = Integer.parseInt(sc.nextLine());

        //Now iterate over all the students and see if they meet the 
        // requirements of the company

        System.out.println("Enter Scores for the Technical Round ");

        ArrayList<TechnicalRound> scores = new ArrayList<TechnicalRound>();

        for (Student stu : RegisteredStudents)
        {
            //System.out.println();
            //check if the branch of the student matches that required
            //by the company
            //sc.nextLine();
            if(coursecriteria.contains(stu.getBranch()))
            {
                System.out.println(stu);
                //The student is eligible to apply in the company
                float marks_scored;
                System.out.println("Enter Score for Roll no. "+stu.getRollNumber());
                String inputString = sc.nextLine();
                marks_scored = Float.parseFloat(inputString);
                stu.ApplyToCompany(Cname, marks_scored);
                TechnicalRound newStu = new TechnicalRound(stu, marks_scored);
                scores.add(newStu);
            }
        }

        Company newCom = new Company(Cname, coursecriteria, student_requirement, scores);
        RegisteredCompanies.add(newCom);
        //sc.close();

        System.out.println();
        System.out.println("A new Company Registered with the Following Details");
        System.out.println();
        System.out.println("Company name : "+ Cname);
        System.out.print("Accepting Students from : ");
        for(String branch : coursecriteria)
        {
            System.out.print(branch + " ");
        }
        System.out.println();
        System.out.println("Number of students required : "+student_requirement);
        System.out.print("Application Status : ");
        if(newCom.getApplicationStatus()==true)
        {
            System.out.println("OPEN");
        }
        else
        {
            System.out.println("CLOSED");
        }
        System.out.println();
        System.out.println();
    }

    public void RemoveStudents()
    {
        //this function removes the accounts of all the placed students

        //delete the record from the main databse of registered students
        System.out.println();
        System.out.print("The Roll nos. of students removed from placements : ");
        ArrayList<Student> temp = new ArrayList<Student>();

        for(Student stu : RegisteredStudents)
        {
            if(stu.getPlacementStatus()==true)
            {
                System.out.print(stu.getRollNumber()+" ");
                temp.add(stu);
            }
        }

        for(Student removal : temp)
        {
            RegisteredStudents.remove(removal);
        }

        //Now iterate over each company and delete the records from there
        for(Company comp : RegisteredCompanies)
        {
            ArrayList<TechnicalRound> students = comp.getEligibleStudents();

            for (TechnicalRound stu : students)
            {
                if(stu.selected==true)
                {
                    students.remove(stu);
                }
            }

            comp.setEligibleStudents(students);
        }
        System.out.println();
    }


    public void RemoveCompanies()
    {
        ArrayList<Company> temp = new ArrayList<Company>();
        System.out.println();
        System.out.print("Companies Removed from Placements : ");
        for (Company comp : RegisteredCompanies)
        {
            if(comp.getApplicationStatus()==false)
            {
                System.out.print(comp.getCompanyName()+" ");
                temp.add(comp);
            }
        }
        System.out.println();
        for(Company comp : temp)
        {
            RegisteredCompanies.remove(comp);
        }
    }

    public void UnplacedStudents()
    {
        //Display the number of unplaced students
        System.out.println();
        int UnplacedStudents = 0;
        for(Student st : RegisteredStudents)
        {
            if(st.getPlacementStatus()==false)
            {
                UnplacedStudents += 1;
            }
        }
        System.out.println("The number of Unplaced Students : "+UnplacedStudents);
        System.out.println();
    }


    public void ApplicationOpenCompany()
    {
        //Display the names of companies with open applications
        System.out.println();
        System.out.println("The names of companies with open application :-");
        for(Company comp : RegisteredCompanies)
        {
            if(comp.getApplicationStatus()==true)
            {
                System.out.println(comp.getCompanyName().toUpperCase());
            }
        }
        System.out.println();
    }


    public void SelectStudents(String companyName)
    {
        //Select students for the company name
        System.out.println();
        int flag = 0;
        System.out.println("The Roll nos. of selected students :- ");
        for(Company comp : RegisteredCompanies)
        {
            if(comp.getCompanyName().toUpperCase().equals(companyName.toUpperCase()))
            {
                flag = 1;
                comp.SelectStudents();
            }
        }
        if(flag==0)
        {
            System.out.println("ERROR : No record of a company with name "+companyName.toUpperCase());
        }
        System.out.println();
    }


    public void DIsplayCompanyDetails(String companyName)
    {
        System.out.println();
        int flag =0;
        for(Company comp : RegisteredCompanies)
        {
            if(comp.getCompanyName().toUpperCase().equals(companyName.toUpperCase()))
            {
                flag = 1;
                comp.DisplayCompany();
            }
        }
        if(flag==0)
        {
            System.out.println("ERROR : No record of a company with name "+companyName.toUpperCase());
        }
        System.out.println();
    }


    public void DisplayStudentDetails(int rollNumber)
    {
        System.out.println();
        int flag = 0;
        for(Student stu : RegisteredStudents)
        {
            if(stu.getRollNumber()==rollNumber)
            {
                flag = 1;
                stu.DisplayStudent();
            }
        }
        if(flag==0)
        {
            System.out.println("ERROR : No record of a student with roll number "+rollNumber);
        }
        System.out.println();

    }

    public void DisplayStudentApplications(int rollNumber)
    {
        System.out.println();
        int flag = 0;
        for(Student stu : RegisteredStudents)
        {
            if(stu.getRollNumber()==rollNumber)
            {
                flag = 1;
                stu.DisplayApplications();
            }
        }
        if(flag==0)
        {
            System.out.println("ERROR : No record of a student with roll number "+rollNumber);
        }
    }

}


class placements
{
    public static void main(String[] args)
    {
        //Create a new Placement office
        PlacementOffice Poffice = new PlacementOffice();
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the Number of students to register : ");
        int NStudents = Integer.parseInt(sc.nextLine());
        Poffice.Register_Students(NStudents);

        do
        {
            System.out.print("ENTER YOUR QUERY : ");
            String Query = sc.nextLine();
            String[] broken_query = Query.split(" ");
            int Qnumber = Integer.parseInt(broken_query[0]);

            switch(Qnumber)
            {
                case 1 : //Register a new Company
                        Poffice.Register_company();
                        break;
                case 2 : //Remove the accounts of placed students
                        Poffice.RemoveStudents();
                        break;
                case 3 : //Remove accounts of companies whose applications are closed
                        Poffice.RemoveCompanies();
                        break;
                case 4 : //Display Number of unplaced students
                        Poffice.UnplacedStudents();
                        break;
                case 5 : //Display names of companies whose applications are open
                        Poffice.ApplicationOpenCompany();
                        break;
                case 6 : //select students
                        if(1<broken_query.length)
                        {
                            //The query was correct
                            String companyName = broken_query[1];
                            Poffice.SelectStudents(companyName);
                        }
                        else
                        {
                            System.out.println("Please Enter a valid Querry");
                        }
                        break;
                case 7 : //Display Company Details
                        if(1<broken_query.length)
                        {
                            String companyName = broken_query[1];
                            Poffice.DIsplayCompanyDetails(companyName);
                        }
                        else
                        {
                            System.out.println("Please Enter a valid Querry");
                        }
                        break;
                case 8 : //Display details of the student
                        if(1<broken_query.length)
                        {
                            int rollNumber = Integer.parseInt(broken_query[1]);
                            Poffice.DisplayStudentDetails(rollNumber);
                        }
                        else
                        {
                            System.out.println("Please Enter a vallid querry");
                        }
                        break;
                case 9 : //Display names of companies for which a student has applied
                        //And their scores in the technical rounds of their company
                        if(1<broken_query.length)
                        {
                            int rollNumber = Integer.parseInt(broken_query[1]);
                            Poffice.DisplayStudentApplications(rollNumber);
                        }
                        else
                        {
                            System.out.println("Please Enter a valid querry");
                        }
                        break;
                default : System.out.println("Please Enter a valid querry");
            }

        }while(Poffice.getRegisteredStudents().isEmpty()==false);

        sc.close();
        System.exit(0);
    }
}