import java.io.*;
import java.util.*;

class new_doctors implements Serializable {
    String dname, specilist, appoint, doc_qual, docPass;
    int droom, did;
    boolean occ;

    public new_doctors(int did, String dname, String specilist, String appoint, String doc_qual, String docPass, int droom, boolean occ) {
        this.did = did;
        this.dname = dname;
        this.specilist = specilist;
        this.appoint = appoint;
        this.doc_qual = doc_qual;
        this.docPass = docPass;
        this.droom = droom;
        this.occ = occ;
    }

    public String getDocPass() {
        return docPass;
    }

    public void setDocPass(String docPass) {
        this.docPass = docPass;
    }

    public String getDname() {
        return dname;
    }

    public String toString() {
        return did + "\t" + dname + "\t" + specilist + "\t" + appoint + "\t" + doc_qual + "\t" + droom;
    }

}

class new_patients implements Serializable {
    String pname, bg, patpass;
    int phone, pid, pp;
    boolean app;

    public new_patients(int pid, String pname, String bg, String patpass, int phone, boolean app, int pp) {
        this.pid = pid;
        this.pname = pname;
        this.bg = bg;
        this.patpass = patpass;
        this.phone = phone;
        this.app = app;
        this.pp = pp;
    }

    public String toString() {
        return pid + "\t" + pname + "\t" + bg + "\t" + "\t" + phone;
    }

    public void setPatPass(String patpass) {
        this.patpass = patpass;
    }
}

class new_appointments implements Serializable {
    int PatientID, Time, appointNo;
    String doctorname, Patientname, Date, appStatus;
    boolean confirm;


    public new_appointments(int appointNo, int PatientID, String Patientname, String doctorname, int Time, String Date, boolean confirm, String appStatus) {
        this.appointNo = appointNo;
        this.PatientID = PatientID;
        this.Patientname = Patientname;
        this.doctorname = doctorname;
        this.Time = Time;
        this.Date = Date;
        this.confirm = confirm;
        this.appStatus = appStatus;
    }

    public String toString() {
        return appointNo + "\t" + PatientID + "\t" + Patientname + "\t" + doctorname + "\t" + Time + "\t" + Date + "\t" + appStatus;
    }
}

class new_prescription implements Serializable {
    int preNo, condition, appointNo;
    String Patientname, Doctorname, medicine, ward, roomno, preStatus;

    public new_prescription(int preNo, int appointNo, String Patientname, String Doctorname, String medicine, int condition, String ward, String roomno, String preStatus) {
        this.preNo = preNo;
        this.appointNo = appointNo;
        this.Patientname = Patientname;
        this.Doctorname = Doctorname;
        this.medicine = medicine;
        this.condition = condition;
        this.ward = ward;
        this.roomno = roomno;
        this.preStatus = preStatus;
    }

    public String toString() {
        return preNo + "\t\t" + appointNo + "\t\t" + Patientname + "\t\t" + Doctorname + "\t" + medicine + "\t\t" + condition + "\t" + ward + "\t" + roomno + "\t" + preStatus;
    }
}

public class Hospitalmanagement {
    public static <Integar> void main(String[] args) throws IOException, ClassNotFoundException {

        int choice = -1, docsect = -1 , patsect = -1, dchoice = -1, pp = 0, status = 0, login = -1, s1 = 1, s2 = 1, s3 = 1, s4 = 1, s5 = 1, s6 = 1;
        String months[] = {
                "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        };
        String[] surgicalward1 = new String[5];
        String[] surgicalward2 = new String[5];
        String[] generalward = new String[5];
        Arrays.fill(generalward, "Not occupied");
        Arrays.fill(surgicalward1, "Not occupied");
        Arrays.fill(surgicalward2, "Not occupied");
        List<String> gwList = Arrays.asList(generalward);
        List<String> sg1list = Arrays.asList(surgicalward1);
        List<String> sg2list = Arrays.asList(surgicalward2);
        Calendar calendar = Calendar.getInstance();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("            * Welcome to Hospital Management System Project in Java *");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Date: " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR));
        System.out.println("\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        /*for(int i = 0; i < surgicalward1.length; i++) {
            System.out.println(surgicalward1[i]);
        }*/
        Scanner input = new Scanner(System.in);
        //Scanner s1 = new Scanner(System.in);
        File doctorFile = new File("doctors.txt");
        File adminFile = new File("admindata.txt");
        File patientFile = new File("patients.txt");
        File appointmentFile = new File("appointments.txt");
        File prescriptionFile = new File("prescriptions.txt");
        File surgicalward1file = new File("Surgicalward1.txt");
        File surgicalward2file = new File("Surgicalward2.txt");
        File generalwardfile = new File("Generalward.txt");
        Scanner adminData = new Scanner(adminFile);
        Writer wr = new FileWriter("admindata.txt");
        BufferedReader reader = new BufferedReader(new FileReader(adminFile));
        wr.write("admin\n"); // admin username
        wr.write("admin"); // admin password
        wr.flush();
        wr.close();
        ArrayList<new_doctors> dal = new ArrayList<new_doctors>();
        ArrayList<new_patients> pal = new ArrayList<new_patients>();
        ArrayList<new_appointments> apal = new ArrayList<new_appointments>();
        ArrayList<new_prescription> preal = new ArrayList<new_prescription>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator li = null;
        ListIterator li1 = null;
        ListIterator li3 = null;
        String adminusername = "";
        String adminpass = "";
        while (adminData.hasNext()) {
            adminusername = adminData.next();
            adminpass = adminData.next();
        }

        int lchoice = -1;
        boolean loginloop = false;
        do {
            try {
                System.out.println("\n                              Login Menu");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("                        1.Admin  2. Doctor  3.Patient");
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Or Press 0 to exit ");
                lchoice = input.nextInt();
                    switch (lchoice) {
                        case 0:
                            loginloop = false;
                            break;
                        case 1:
                            boolean adminloop = false;
                            int acoice = 0;
                            do {
                                try {
                                    System.out.println("Press 1 to login : ");
                                    System.out.println("Press 2 for updating password");
                                    System.out.println("Press 0 to go back");
                                    acoice = input.nextInt();
                                    switch (acoice) {
                                        case 0:
                                            loginloop = true;
                                            break;
                                        case 1:
                                            String aId;
                                            String aPs;
                                            System.out.println("Enter your login ID : ");
                                            aId = input.next();
                                            System.out.println("Enter password : ");
                                            aPs = input.next();
                                            if (Objects.equals(adminusername, aId) && Objects.equals(adminpass, aPs)) {
                                                System.out.println("Login Successfull");
                                                System.out.println("Welcome to the menu ");
                                                boolean adminmenu = false;
                                                do {
                                                    try {
                                                        do {
                                                            System.out.println("\n                                    MAIN MENU");
                                                            System.out.println("-----------------------------------------------------------------------------------");
                                                            System.out.println("                           1.Doctors  2. Patients  3.Wards  ");
                                                            System.out.println("-----------------------------------------------------------------------------------");
                                                            System.out.println("Choose one option or press zero to go back to login menu");
                                                            choice = input.nextInt();
                                                            switch (choice) {
                                                                case 0:
                                                                    dchoice = 1;
                                                                    break;
                                                                case 1:
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    System.out.println("                      DOCTOR SECTION");
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    boolean dsectionloop = false;
                                                                    do {
                                                                        try {
                                                                            do {
                                                                                System.out.println("Press 1 to enter new doc");
                                                                                System.out.println("Press 2 to Display docs");
                                                                                System.out.println("Press 3 to Search");
                                                                                System.out.println("Press 4 to Delete");
                                                                                System.out.println("Press 5 to Edit");
                                                                                System.out.println("Press 6 to View Available doctors ");
                                                                                System.out.println("Press 7 to view prescriptions ");
                                                                                System.out.println("Press 0 to go back to menu");
                                                                                System.out.println("Enter your choice: ");
                                                                                dchoice = input.nextInt();

                                                                                switch (dchoice) {
                                                                                    case 0:
                                                                                        break;
                                                                                    case 1:
                                                                                        System.out.println("How many entries you want to enter : ");
                                                                                        int n = input.nextInt();
                                                                                        for (int i = 1; i <= n; i++) {
                                                                                            System.out.println("Enter the details of " + i + " doctor below");
                                                                                            int did = dal.size() + 1;
                                                                                            System.out.print("name:-");
                                                                                            String dname = input.next();
                                                                                            System.out.print("specilization:-  ");
                                                                                            String specilist = input.next();
                                                                                            System.out.print("work time:-");
                                                                                            String appoint = input.next();
                                                                                            System.out.print("qualification:-");
                                                                                            String doc_qual = input.next();
                                                                                            System.out.print("room no.:-");
                                                                                            int droom = input.nextInt();
                                                                                            System.out.println("Password");
                                                                                            String docPass = input.next();
                                                                                            boolean occ = false;
                                                                                            dal.add(new new_doctors(did, dname, specilist, appoint, doc_qual, docPass, droom, occ));
                                                                                        }
                                                                                        oos = new ObjectOutputStream(new FileOutputStream(doctorFile));
                                                                                        oos.writeObject(dal);
                                                                                        oos.close();
                                                                                        break;

                                                                                    case 2:
                                                                                        if (doctorFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                                            dal = (ArrayList<new_doctors>) ois.readObject();
                                                                                            ois.close();
                                                                                            System.out.println("Id\tName\tDep\tWork Time\tQual\tRoom no \n");
                                                                                            System.out.println("----------------------------------------------------------");
                                                                                            li = dal.listIterator();
                                                                                            while (li.hasNext())
                                                                                                System.out.println(li.next() + "\n");
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;

                                                                                    case 3:
                                                                                        if (doctorFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                                            dal = (ArrayList<new_doctors>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the Doctor ID to search : ");
                                                                                            int did1 = input.nextInt();
                                                                                            li = dal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_doctors d = (new_doctors) li.next();
                                                                                                if (d.did == did1) {
                                                                                                    System.out.println(d);
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (!found) {
                                                                                                System.out.println("Record not found!");
                                                                                                System.out.println("---------------------------------------");
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;
                                                                                    case 4:
                                                                                        if (doctorFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                                            dal = (ArrayList<new_doctors>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the Doctor ID to Delete : ");
                                                                                            int did1 = input.nextInt();
                                                                                            li = dal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_doctors d = (new_doctors) li.next();
                                                                                                if (d.did == did1) {
                                                                                                    li.remove();
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (found) {
                                                                                                oos = new ObjectOutputStream(new FileOutputStream(doctorFile));
                                                                                                oos.writeObject(dal);
                                                                                                oos.close();
                                                                                                System.out.println("Record Deleted Successfully!");

                                                                                            } else {
                                                                                                System.out.println("Record not found!");
                                                                                            }
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;
                                                                                    case 5:
                                                                                        if (doctorFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                                            dal = (ArrayList<new_doctors>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the Doctor ID to Edit : ");
                                                                                            int did1 = input.nextInt();
                                                                                            li = dal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_doctors d = (new_doctors) li.next();
                                                                                                if (d.did == did1) {
                                                                                                    System.out.print("Enter the new name:-");
                                                                                                    d.dname = input.next();
                                                                                                    System.out.print("Enter the new specilization:-  ");
                                                                                                    d.specilist = input.next();
                                                                                                    System.out.print("Enter the new work time:-");
                                                                                                    d.appoint = input.next();
                                                                                                    System.out.print("Enter the new qualification:-");
                                                                                                    d.doc_qual = input.next();
                                                                                                    System.out.print("Enter the new room no.:-");
                                                                                                    d.droom = input.nextInt();
                                                                                                    li.set(new new_doctors(d.did, d.dname, d.specilist, d.appoint, d.doc_qual, d.docPass, d.droom, d.occ));
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (found) {
                                                                                                oos = new ObjectOutputStream(new FileOutputStream(doctorFile));
                                                                                                oos.writeObject(dal);
                                                                                                oos.close();
                                                                                                System.out.println("Record Edited Successfully!");

                                                                                            } else {
                                                                                                System.out.println("Record not found!");
                                                                                            }
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }

                                                                                        break;
                                                                                    case 6:
                                                                                        ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                                        dal = (ArrayList<new_doctors>) ois.readObject();
                                                                                        ois.close();
                                                                                        li = dal.listIterator();
                                                                                        while (li.hasNext()) {
                                                                                            new_doctors d = (new_doctors) li.next();
                                                                                            if (!d.occ) {
                                                                                                System.out.println(d);
                                                                                            }
                                                                                        }
                                                                                        System.out.println("---------------------------------------");
                                                                                        break;
                                                                                    case 7:
                                                                                        if (prescriptionFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(prescriptionFile));
                                                                                            preal = (ArrayList<new_prescription>) ois.readObject();
                                                                                            ois.close();
                                                                                            ArrayList<Integer> prescriptionNO = new ArrayList<>();
                                                                                            li = preal.listIterator();
                                                                                            li1 = preal.listIterator();
                                                                                            System.out.println("=============== Prescriptions ===========");
                                                                                            System.out.println("Pre no  App no  Pat name  Doc name  Med  Condition  Ward \t Bed no \t   Status ");
                                                                                            while (li.hasNext()) {
                                                                                                new_prescription prescription = (new_prescription) li.next();
                                                                                                System.out.println(prescription);
                                                                                                prescriptionNO.add(prescription.preNo);
                                                                                                //else System.out.println("No awaiting appointments");
                                                                                                //System.out.println(prescriptionNO);
                                                                                            }
                                                                                            System.out.println("--------------------------------------------------");
                                                                                            System.out.println("Select a prescription to confirm and allot ward or press zero to go back");
                                                                                            int select = input.nextInt();
                                                                                            //System.out.println("reading while now");
                                                                                            if (select != 0) {
                                                                                                while (li1.hasNext()) {
                                                                                                    new_prescription prescription1 = (new_prescription) li1.next();
                                                                                                    if (select == prescription1.preNo) {
                                                                                                        System.out.println("=========== Selected Prescription ========");
                                                                                                        System.out.println(prescription1);
                                                                                                        System.out.println("-------------------------------------");
                                                                                                        System.out.println("Press 1 to confirm and 2 to go back");
                                                                                                        int c = input.nextInt();
                                                                                                        switch (c) {
                                                                                                            case 1:
                                                                                                                if (prescription1.ward.equalsIgnoreCase("surgical") && prescription1.condition <= 5) {
                                                                                                                    if (surgicalward2file.isFile()) {
                                                                                                                        ois = new ObjectInputStream(new FileInputStream(surgicalward2file));
                                                                                                                        sg2list = (List<String>) ois.readObject();
                                                                                                                        ois.close();
                                                                                                                    } else
                                                                                                                        System.out.println("No data in surgical ward 2 found!");

                                                                                                                    prescription1.ward = "Surgical 2";
                                                                                                                    prescription1.preStatus = "Prescribed";
                                                                                                                    int sg2_index = sg2list.indexOf("Not occupied");
                                                                                                                    prescription1.roomno = String.valueOf(sg2_index + 1);
                                                                                                                    li1.set(new new_prescription(prescription1.preNo, prescription1.appointNo, prescription1.Patientname, prescription1.Doctorname, prescription1.medicine, prescription1.condition, prescription1.ward, prescription1.roomno, prescription1.preStatus));
                                                                                                                    sg2list.set(sg2_index, "Occupied");
                                                                                                                    System.out.println("Successfully added to Surgical Ward 2");
                                                                                                                } else if (prescription1.ward.equalsIgnoreCase("surgical") && prescription1.condition > 5) {
                                                                                                                    if (surgicalward1file.isFile()) {
                                                                                                                        ois = new ObjectInputStream(new FileInputStream(surgicalward1file));
                                                                                                                        sg1list = (List<String>) ois.readObject();
                                                                                                                        ois.close();
                                                                                                                    } else
                                                                                                                        System.out.println("No data in surgical ward 1 found!");

                                                                                                                    prescription1.ward = "Surgical 1";
                                                                                                                    int sg1_index = sg1list.indexOf("Not occupied");
                                                                                                                    prescription1.roomno = String.valueOf(sg1_index + 1);
                                                                                                                    prescription1.preStatus = "Prescribed";
                                                                                                                    li1.set(new new_prescription(prescription1.preNo, prescription1.appointNo, prescription1.Patientname, prescription1.Doctorname, prescription1.medicine, prescription1.condition, prescription1.ward, prescription1.roomno, prescription1.preStatus));
                                                                                                                    sg1list.set(sg1_index, "Occupied");
                                                                                                                    System.out.println("Successfully added to Surgical Ward 1");
                                                                                                                } else {
                                                                                                                    if (generalwardfile.isFile()) {
                                                                                                                        ois = new ObjectInputStream(new FileInputStream(generalwardfile));
                                                                                                                        gwList = (List<String>) ois.readObject();
                                                                                                                        ois.close();
                                                                                                                    } else
                                                                                                                        System.out.println("general ward file not found");
                                                                                                                    System.out.println(prescription1);
                                                                                                                    prescription1.ward = "General ward";
                                                                                                                    int index = gwList.indexOf("Not occupied");
                                                                                                                    prescription1.roomno = String.valueOf(index + 1);
                                                                                                                    prescription1.preStatus = "Prescribed";
                                                                                                                    li1.set(new new_prescription(prescription1.preNo, prescription1.appointNo, prescription1.Patientname, prescription1.Doctorname, prescription1.medicine, prescription1.condition, prescription1.ward, prescription1.roomno, prescription1.preStatus));
                                                                                                                    System.out.println("Successfully added to General ward");
                                                                                                                    gwList.set(index, "Occupied");
                                                                                                                    System.out.println(preal);
                                                                                                                    //System.out.println(gwList.indexOf("Not occupied"));
                                                                                                                    //System.out.println(gwList);
                                                                                                                }
                                                                                                                select = 0;
                                                                                                                break;
                                                                                                            case 2:
                                                                                                                System.out.println("Appointment declined");
                                                                                                                break;
                                                                                                        }

                                                                                                    }
                                                                                                    oos = new ObjectOutputStream(new FileOutputStream(prescriptionFile));
                                                                                                    oos.writeObject(preal);
                                                                                                    oos.close();
                                                                                                    oos = new ObjectOutputStream(new FileOutputStream(generalwardfile));
                                                                                                    oos.writeObject(gwList);
                                                                                                    oos.close();
                                                                                                    oos = new ObjectOutputStream(new FileOutputStream(surgicalward1file));
                                                                                                    oos.writeObject(sg1list);
                                                                                                    oos.close();
                                                                                                    oos = new ObjectOutputStream(new FileOutputStream(surgicalward2file));
                                                                                                    oos.writeObject(sg2list);
                                                                                                    oos.close();
                                                                                                }
                                                                                                //System.out.println("while not read");
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("File doesnt exist");
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                            while (dchoice != 0);
                                                                            dsectionloop = false;
                                                                        } catch (Exception e) {
                                                                            input.nextLine();
                                                                            System.out.println("Doctor section is throwing an exception");
                                                                            dsectionloop = true;
                                                                        }
                                                                    }while (dsectionloop);
                                                                    break;
                                                                case 2:
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    System.out.println("                     PATIENT SECTION");
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    if (patientFile.isFile()) {
                                                                        ois = new ObjectInputStream(new FileInputStream(patientFile));
                                                                        pal = (ArrayList<new_patients>) ois.readObject();
                                                                        ois.close();
                                                                    }
                                                                    int pchoice;
                                                                    boolean patsecloop = false;
                                                                    do {
                                                                        try {
                                                                            do {
                                                                                System.out.println("Press 1 to enter new patient");
                                                                                System.out.println("Press 2 to Display patients");
                                                                                System.out.println("Press 3 to Search patient");
                                                                                System.out.println("Press 4 to Delete patient ");
                                                                                System.out.println("Press 5 to Edit patient");
                                                                                System.out.println("Press 6 to View Awaiting Appointments");
                                                                                System.out.println("Press 7 to View All Appointments");
                                                                                System.out.println("Press 0 to go back to menu");
                                                                                System.out.println("Enter your choice: ");
                                                                                pchoice = input.nextInt();

                                                                                switch (pchoice) {
                                                                                    case 1:
                                                                                        System.out.println("How many entries you want to enter : ");
                                                                                        int n = input.nextInt();
                                                                                        for (int i = 1; i <= n; i++) {
                                                                                            System.out.println("Enter the details of " + i + " patient below");
                                                                                            int pid = pal.size() + 1;
                                                                                            System.out.print("name:-");
                                                                                            String pname = input.next();
                                                                                            System.out.print("Blood Group:-");
                                                                                            String bg = input.next();
                                                                                            System.out.print("Phone no.:-");
                                                                                            int phone = input.nextInt();
                                                                                            System.out.println("Password :");
                                                                                            String patpass = input.next();
                                                                                            boolean app = false;
                                                                                            pp = 0;
                                                                                            pal.add(new new_patients(pid, pname, bg, patpass, phone, app, pp));
                                                                                        }
                                                                                        oos = new ObjectOutputStream(new FileOutputStream(patientFile));
                                                                                        oos.writeObject(pal);
                                                                                        oos.close();
                                                                                        break;
                                                                                    case 2:
                                                                                        if (patientFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(patientFile));
                                                                                            pal = (ArrayList<new_patients>) ois.readObject();
                                                                                            ois.close();
                                                                                            li = pal.listIterator();
                                                                                            while (li.hasNext())
                                                                                                System.out.println(li.next() + "\n");
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;

                                                                                    case 3:
                                                                                        if (patientFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(patientFile));
                                                                                            pal = (ArrayList<new_patients>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the patients ID to search : ");
                                                                                            int pid1 = input.nextInt();
                                                                                            li = pal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_patients p = (new_patients) li.next();
                                                                                                if (p.pid == pid1) {
                                                                                                    System.out.println(p);
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (!found) {
                                                                                                System.out.println("Record not found!");
                                                                                                System.out.println("---------------------------------------");
                                                                                            }
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;
                                                                                    case 4:
                                                                                        if (patientFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(patientFile));
                                                                                            pal = (ArrayList<new_patients>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the Patients ID to Delete : ");
                                                                                            int pid1 = input.nextInt();
                                                                                            li = pal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_patients p = (new_patients) li.next();
                                                                                                if (p.pid == pid1) {
                                                                                                    li.remove();
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (found) {
                                                                                                oos = new ObjectOutputStream(new FileOutputStream(patientFile));
                                                                                                oos.writeObject(dal);
                                                                                                oos.close();
                                                                                                System.out.println("Record Deleted Successfully!");

                                                                                            } else {
                                                                                                System.out.println("Record not found!");
                                                                                            }
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;
                                                                                    case 5:
                                                                                        if (patientFile.isFile()) {
                                                                                            ois = new ObjectInputStream(new FileInputStream(patientFile));
                                                                                            pal = (ArrayList<new_patients>) ois.readObject();
                                                                                            ois.close();
                                                                                            boolean found = false;
                                                                                            System.out.println("Enter the Patient ID to Edit : ");
                                                                                            int pid1 = input.nextInt();
                                                                                            li = pal.listIterator();
                                                                                            while (li.hasNext()) {
                                                                                                new_patients p = (new_patients) li.next();
                                                                                                if (p.pid == pid1) {
                                                                                                    System.out.print("Enter the new name:-");
                                                                                                    p.pname = input.next();
                                                                                                    System.out.print("Enter the new blood group :-");
                                                                                                    p.bg = input.next();
                                                                                                    System.out.print("Enter the new phone no .:-");
                                                                                                    p.phone = input.nextInt();
                                                                                                    System.out.println("Enter the appointment state");
                                                                                                    li.set(new new_patients(p.pid, p.pname, p.bg, p.patpass, p.phone, p.app, p.pp));
                                                                                                    found = true;
                                                                                                }
                                                                                            }
                                                                                            if (found) {
                                                                                                oos = new ObjectOutputStream(new FileOutputStream(patientFile));
                                                                                                oos.writeObject(pal);
                                                                                                oos.close();
                                                                                                System.out.println("Record Edited Successfully!");

                                                                                            } else {
                                                                                                System.out.println("Record not found!");
                                                                                            }
                                                                                            System.out.println("---------------------------------------");
                                                                                        } else {
                                                                                            System.out.println("File does not exist!!");
                                                                                        }
                                                                                        break;
                                                                                    case 6:
                                                                                        ois = new ObjectInputStream(new FileInputStream(appointmentFile));
                                                                                        apal = (ArrayList<new_appointments>) ois.readObject();
                                                                                        ois.close();
                                                                                        li = apal.listIterator();
                                                                                        li1 = apal.listIterator();
                                                                                        //while (li1.hasNext()) {
                                                                                        System.out.println("================== Appointments ================\n");
                                                                                        System.out.println("App# Id  Name   Doc  Time  Date     Status \n");
                                                                                        while (li.hasNext()) {
                                                                                            new_appointments app = (new_appointments) li.next();
                                                                                            System.out.println(app);
                                                                                            //else System.out.println("No awaiting appointments");
                                                                                        }
                                                                                        System.out.println("Select an appointment to confirm ");
                                                                                        int select = input.nextInt();
                                                                                        while (li1.hasNext()) {
                                                                                            new_appointments app1 = (new_appointments) li1.next();
                                                                                            if (select == app1.appointNo) {
                                                                                                System.out.println(app1);
                                                                                                System.out.println("-------------------------------------");
                                                                                                System.out.println("Press 1 to confirm and 2 to decline");
                                                                                                int c = input.nextInt();
                                                                                                switch (c) {
                                                                                                    case 1:
                                                                                                        //app1.confirm = true;
                                                                                                        System.out.println("Appointment Confirmed");
                                                                                                        app1.appStatus = "Confirmed";
                                                                                                        li1.set(new new_appointments(app1.appointNo, app1.PatientID, app1.Patientname, app1.doctorname, app1.Time, app1.Date, app1.confirm, app1.appStatus));
                                                                                                        break;
                                                                                                    case 2:
                                                                                                        app1.confirm = false;
                                                                                                        System.out.println("Appointment declined");
                                                                                                        app1.appStatus = "Declined";
                                                                                                        li1.set(new new_appointments(app1.appointNo, app1.PatientID, app1.Patientname, app1.doctorname, app1.Time, app1.Date, app1.confirm, app1.appStatus));
                                                                                                        break;
                                                                                                }
                                                                /*if (c == 1) {
                                                                    app1.confirm = true;
                                                                    System.out.println("Appointment Confirmed");
                                                                    app1.appStatus = "Confirmed";
                                                                } else if (c == 2) {
                                                                    app1.confirm = false;
                                                                    System.out.println("Appointment declined");
                                                                    app1.appStatus = "Declined";
                                                                }*/
                                                                                                //else System.out.println("Invalid user input");
                                                                                                //li1.set(new new_appointments(app1.appointNo, app1.PatientID, app1.Patientname, app1.doctorname, app1.Time, app1.Date, app1.confirm , app1.appStatus));
                                                                                                oos = new ObjectOutputStream(new FileOutputStream(appointmentFile));
                                                                                                oos.writeObject(apal);
                                                                                                oos.close();
                                                                                            }
                                                                                            //System.out.println(app1);

                                                                                        }
                                                                                        break;
                                                                                    case 7:
                                                                                        ois = new ObjectInputStream(new FileInputStream(appointmentFile));
                                                                                        apal = (ArrayList<new_appointments>) ois.readObject();
                                                                                        ois.close();
                                                                                        li = apal.listIterator();
                                                                                        while (li.hasNext()) {
                                                                                            new_appointments app = (new_appointments) li.next();
                                                                                            System.out.println(app);
                                                                                        }
                                                                                        System.out.println("----------------------------------------------");
                                                                                        break;

                                                                                }
                                            /*System.out.println("Press 0 again to go back to menu");
                                            status = input.nextInt();*/
                                                                            }
                                                                            while (pchoice != 0);
                                                                            patsecloop = false;
                                                                        } catch (Exception e) {
                                                                            input.nextLine();
                                                                            System.out.println("Patient Section is throwing an exception");
                                                                            patsecloop = true;
                                                                        }
                                                                    }while(patsecloop);
                                                                    break;

                                                                case 3:
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    System.out.println("                                    WARDS");
                                                                    System.out.println("--------------------------------------------------------------------------------");
                                                                    int wchoice;
                                                                    boolean wardsloop = false;
                                                                    do {
                                                                        try {
                                                                            do {
                                                                                System.out.println("Press 1 to view General ward");
                                                                                System.out.println("Press 2 to view Surgical ward 1 ");
                                                                                System.out.println("Press 3 to view Surgical ward 2");
                                                                                System.out.println("Press 0 to go back to main menu");
                                                                                wchoice = input.nextInt();
                                                                                switch (wchoice) {
                                                                                    case 0:
                                                                                        break;
                                                                                    case 1:
                                                                                        ois = new ObjectInputStream(new FileInputStream(generalwardfile));
                                                                                        gwList = (List<String>) ois.readObject();
                                                                                        ois.close();
                                                                                        //li = gwList.listIterator();
                                                                                        gwList.toArray(generalward);
                                                                                        for (int i = 0; i < generalward.length; i++) {
                                                                                            System.out.println("Bed no " + (i + 1) + " " + generalward[i]);
                                                                                        }
                                                                                        break;
                                                                                    case 2:
                                                                                        ois = new ObjectInputStream(new FileInputStream(surgicalward1file));
                                                                                        sg1list = (List<String>) ois.readObject();
                                                                                        ois.close();
                                                                                        //li = gwList.listIterator();
                                                                                        sg1list.toArray(surgicalward1);
                                                                                        for (int i = 0; i < surgicalward1.length; i++) {
                                                                                            System.out.println("Bed no " + (i + 1) + " " + surgicalward1[i]);
                                                                                        }
                                                                                        break;
                                                                                    case 3:
                                                                                        ois = new ObjectInputStream(new FileInputStream(surgicalward2file));
                                                                                        sg2list = (List<String>) ois.readObject();
                                                                                        ois.close();
                                                                                        //li = gwList.listIterator();
                                                                                        sg2list.toArray(surgicalward2);
                                                                                        for (int i = 0; i < surgicalward2.length; i++) {
                                                                                            System.out.println("Bed no " + (i + 1) + " " + surgicalward2[i]);
                                                                                        }
                                                                                        break;
                                                                                    default:
                                                                                        //System.out.println("Invalid input");
                                                                                        throw new IllegalStateException("Unexpected value: " + wchoice);
                                                                                }
                                                                            } while (wchoice != 0);
                                                                            wardsloop = false;
                                                                        } catch (Exception e) {
                                                                            input.nextLine();
                                                                            //System.out.println(e);
                                                                            System.out.println("Invalid input");
                                                                            wardsloop = true;
                                                                        }
                                                                    }while (wardsloop);
                                                            }
                                                        } while (choice != 0 || dchoice == 0);
                                                        adminmenu = false;
                                                    } catch (Exception e) {
                                                        input.nextLine();
                                                        System.out.println("Admin main menu is throwing exception");
                                                        adminmenu = true;
                                                    }
                                                }while(adminmenu);
                                            }
                                            else {
                                                System.out.println("Invalid username or password");
                                            }
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            throw new IllegalStateException("Unexpected value: " + acoice);
                                    }
                                    adminloop = false;
                                } catch (Exception e) {
                                    input.nextLine();
                                    System.out.println("Admin is throwing exception");
                                    adminloop = true;
                                    System.out.println(acoice);
                                }
                            } while (adminloop);
                            break;
                        case 2:
                            int dchoice1;
                            System.out.println("Press 1 to update the password ");
                            System.out.println("Press 2 to login ");
                            System.out.println("Press 0 to go back");
                            dchoice1 = input.nextInt();
                            ois = new ObjectInputStream(new FileInputStream(doctorFile));
                            dal = (ArrayList<new_doctors>) ois.readObject();
                            ois.close();
                            li3 = dal.listIterator();
                            li1 = dal.listIterator();
                            while (li1.hasNext()) {
                                new_doctors dap = (new_doctors) li1.next();
                            }
                            switch (dchoice1) {
                                case 0:
                                    break;
                                case 1:
                                    ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                    dal = (ArrayList<new_doctors>) ois.readObject();
                                    ois.close();
                                    li = dal.listIterator();
                                    new_doctors d = (new_doctors) li.next();
                                    System.out.println("Enter the doc id : ");
                                    int did1 = input.nextInt();
                                    if (d.did == did1) {
                                        System.out.println("Doctor I'd found");
                                    } else System.out.println("Invalid doctor id");
                                    System.out.println("Enter the password you want to set : ");
                                    String docP1 = input.next();
                                    d.setDocPass(docP1);
                                    System.out.println("Password updated Successfully");

                                    li.set(new new_doctors(d.did, d.dname, d.specilist, d.appoint, d.doc_qual, d.docPass, d.droom, d.occ));
                                    oos = new ObjectOutputStream(new FileOutputStream(doctorFile));
                                    oos.writeObject(dal);
                                    oos.close();
                                    break;
                                case 2:
                                    if (doctorFile.isFile()) {
                                        ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                        dal = (ArrayList<new_doctors>) ois.readObject();
                                        ois.close();
                                        boolean found = false;
                                        System.out.println("Enter the Doctor ID : ");
                                        did1 = input.nextInt();
                                        String docpass = "";
                                        li = dal.listIterator();
                                        while (li.hasNext()) {
                                            new_doctors d2 = (new_doctors) li.next();
                                            if (d2.did == did1) {
                                                System.out.println("Doctor id found");
                                                System.out.println(d2.docPass);
                                                System.out.println(d2.dname);
                                                System.out.println("Enter the password");
                                                docP1 = input.next();
                                                docpass = d2.docPass;
                                                if (docpass.equals(docP1)) {
                                                    System.out.println("logged in successfully ");
                                                    int x = 0;
                                                    boolean docloop = false;
                                                    do {
                                                        try {
                                                            do {
                                                                System.out.println("================== Doctor Menu ====================\n");
                                                                System.out.println("1:View Profile");
                                                                System.out.println("2:Update Profile");
                                                                System.out.println("3:View appointments");
                                                                System.out.println("4:View Prescriptions");
                                                                System.out.println("Or Press 0 to go back to login menu");
                                                                int docChoice;
                                                                docChoice = input.nextInt();
                                                                String doctname = "";
                                                                switch (docChoice) {
                                                                    case 0:
                                                                        docsect = 0;
                                                                        //System.out.println(x);
                                                                        break;
                                                                    case 1:
                                                                        System.out.println("------------------Doc Profile-----------------------------");
                                                                        System.out.println("Id\tName\tDep\tWork Time\tQual\tRoom no \n");
                                                                        System.out.println("----------------------------------------------------------");
                                                                        System.out.println(d2);
                                                                        doctname = d2.dname;
                                                                        docsect = 1;
                                                                        break;
                                                                    case 2:
                                                                        while (li.hasNext()) {
                                                                            //new_doctors d2 = (new_doctors) li.next();
                                                                            System.out.print("Enter the new name:-");
                                                                            d2.dname = input.next();
                                                                            System.out.print("Enter the new specilization:-  ");
                                                                            d2.specilist = input.next();
                                                                            System.out.print("Enter the new work time:-");
                                                                            d2.appoint = input.next();
                                                                            System.out.print("Enter the new qualification:-");
                                                                            d2.doc_qual = input.next();
                                                                            System.out.print("Enter the new room no.:-");
                                                                            d2.droom = input.nextInt();
                                                                            li.set(new new_doctors(d2.did, d2.dname, d2.specilist, d2.appoint, d2.doc_qual, d2.docPass, d2.droom, d2.occ));
                                                                            oos = new ObjectOutputStream(new FileOutputStream(doctorFile));
                                                                            oos.writeObject(dal);
                                                                            oos.close();
                                                                            System.out.println("Record Edited Successfully!");
                                                                            docsect = 1;
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 3:
                                                                        ois = new ObjectInputStream(new FileInputStream(appointmentFile));
                                                                        apal = (ArrayList<new_appointments>) ois.readObject();
                                                                        ois.close();
                                                                        li = apal.listIterator();
                                                                        li1 = dal.listIterator();
                                                                        int appointno = 0;
                                                                        doctname = d2.dname;
                                                                        new_appointments app = null;
                                                                        ArrayList<Integer> appointmentno = new ArrayList<>();
                                                                        ArrayList<String> patName = new ArrayList<>();
                                                                        int prech = -1;
                                                                        appointmentno.add(0, 0);
                                                                        patName.add(0, "null");
                                                                        System.out.println("================== Appointments ================\n");
                                                                        System.out.println("App# Id  Name   Doc  Time  Date     Status \n");
                                                                        while (li.hasNext()) {
                                                                            app = (new_appointments) li.next();
                                                                            if (Objects.equals(app.doctorname, doctname) && app.appStatus.equals("Confirmed") && app.confirm == false) {
                                                                                System.out.println(app);
                                                                                appointmentno.add(app.appointNo);
                                                                                patName.add(app.Patientname);
                                                                            }
                                                                        }
                                                                        if (appointmentno.size() != 1) {
                                                                            System.out.println("----------------------------------------------\n");
                                                                            System.out.println("Select an appointment to write Prescription");
                                                                            int p = input.nextInt();
                                                                            //System.out.println(app.appointNo);
                                                                            //System.out.println(appointmentno);
                                                                            System.out.println("=================Selected appointment===========");
                                                                            if (appointmentno.contains(p)) {
                                                                                //System.out.println(apal);
                                                                                int index = appointmentno.indexOf(p);
                                                                                //System.out.println(index);
                                                                                //System.out.println(patName.get(index));
                                                                                System.out.println(apal.get(p - 1));
                                                                                System.out.println("-------------------------------------");
                                                                                System.out.println("Press 1 to Prescribe and 2 to go back");

                                                                                int c = input.nextInt();
                                                                                switch (c) {
                                                                                    case 1:
                                                                            /*ois = new ObjectInputStream(new FileInputStream(prescriptionFile));
                                                                            preal = (ArrayList<new_prescription>) ois.readObject();
                                                                            ois.close();*/
                                                                                        int preNo = preal.size() + 1;
                                                                                        int appointNo = appointmentno.get(index);
                                                                                        String Patientname = patName.get(index);
                                                                                        String Doctorname = d2.dname;
                                                                                        //System.out.println(preal.size());
                                                                                        System.out.println("Enter the medicine to prescribe");
                                                                                        String medicine = input.next();
                                                                                        System.out.println("Enter the Condition on the scale of 1-10");
                                                                                        int condition = input.nextInt();
                                                                                        System.out.println("Enter the type either surgical or general");
                                                                                        String ward = input.next();
                                                                                        String roomno = "Not assigned";
                                                                                        String preStatus = "Awaiting";
                                                                                        preal.add(new new_prescription(preNo, appointNo, Patientname, Doctorname, medicine, condition, ward, roomno, preStatus));
                                                                            /*System.out.println(appointmentno.get(index));
                                                                            System.out.println(patName.get(index));*/
                                                                                        System.out.println("Prescription Sucessfully added ");
                                                                                        app.confirm = true;
                                                                                        li.set(new new_appointments(app.appointNo, app.PatientID, app.Patientname, app.doctorname, app.Time, app.Date, app.confirm, app.appStatus));
                                                                                        oos = new ObjectOutputStream(new FileOutputStream(prescriptionFile));
                                                                                        oos.writeObject(preal);
                                                                                        oos.close();
                                                                                        //System.out.println(app.confirm);
                                                                                        oos = new ObjectOutputStream(new FileOutputStream(appointmentFile));
                                                                                        oos.writeObject(apal);
                                                                                        oos.close();
                                                                                        //System.out.println(preal);
                                                                                        System.out.println("Press 1 to go back");
                                                                                        docsect = input.nextInt();
                                                                                        while (docsect != 1) {
                                                                                            System.out.println(docsect);
                                                                                            System.out.println("invalid input");
                                                                                            System.out.println("Enter again");
                                                                                            docsect = input.nextInt();
                                                                                        }
                                                                                        break;
                                                                                    case 2:
                                                                                        prech = 0;
                                                                                        docsect = 1;
                                                                                        break;
                                                                                    default:
                                                                                        System.out.println("Invalid input");
                                                                                }
                                                                            }
                                                                        } else {
                                                                            System.out.println("No Confirm appointments yet!!!");
                                                                            System.out.println("");
                                                                        }
                                                                        docsect = 1;
                                                                        break;
                                                                    case 4:
                                                                        ois = new ObjectInputStream(new FileInputStream(prescriptionFile));
                                                                        preal = (ArrayList<new_prescription>) ois.readObject();
                                                                        ois.close();
                                                                        ArrayList<Integer> preno = new ArrayList<>();
                                                                        li = preal.listIterator();
                                                                        li1 = dal.listIterator();
                                                                        doctname = d2.dname;
                                                                        System.out.println(doctname);
                                                                        //System.out.println(preal);
                                                                        new_prescription pres = null;
                                                                        System.out.println("================== Confirmed Prescriptions ================\n");
                                                                        System.out.println("Pre no  App no  Pat name  Doc name   Med   Condition    Ward \t   Bed no \tStatus \n");
                                                                        while (li.hasNext()) {
                                                                            pres = (new_prescription) li.next();
                                                                            if (Objects.equals(pres.Doctorname, doctname) && pres.preStatus.equals("Prescribed")) {
                                                                                System.out.println(pres);
                                                                                System.out.println("");
                                                                                preno.add(pres.preNo);
                                                                            }
                                                                        }
                                                                        //System.out.println(preno);
                                                                        if (preno.isEmpty()) {
                                                                            System.out.println("No Confirmed Prescriptions added yet");
                                                                            System.out.println("");
                                                                        }
                                                                        docsect = 1;
                                                                        break;
                                                                }
                                                            } while (docsect == 1);
                                                            docloop = false;
                                                        } catch (Exception e) {
                                                            input.nextLine();
                                                            System.out.println("Invalid input.. Try again");
                                                            docloop = true;
                                                        }
                                                    }while(docloop);
                                                } else System.out.println("Invalid password");
                                                found = true;
                                            }

                                        }
                                        if (!found) {
                                            System.out.println("Record not found!");
                                            System.out.println("---------------------------------------");
                                        }
                                    } else {
                                        System.out.println("File does not exist!!");
                                    }
                                    break;

                            }
                            break;
                        case 3:
                            int pchoice1;
                            System.out.println("Press 1 to update password");
                            System.out.println("Press 2 to Login ");
                            System.out.println("Press 0 to go to back login menu ");
                            pchoice1 = input.nextInt();
                            switch (pchoice1) {
                                case 1:
                                    ois = new ObjectInputStream(new FileInputStream(patientFile));
                                    pal = (ArrayList<new_patients>) ois.readObject();
                                    ois.close();
                                    li = pal.listIterator();
                                    new_patients p = (new_patients) li.next();
                                    System.out.println("Enter the patient id : ");
                                    int pid1 = input.nextInt();
                                    if (p.pid == pid1) {
                                        System.out.println("Patient I'd found");
                                    } else System.out.println("Invalid Patient id");
                                    System.out.println("Enter the password you want to set : ");
                                    String patP1 = input.next();
                                    p.setPatPass(patP1);
                                    System.out.println("Password updated Successfully");

                                    li.set(new new_patients(p.pid, p.pname, p.bg, p.patpass, p.phone, p.app, p.pp));
                                    oos = new ObjectOutputStream(new FileOutputStream(patientFile));
                                    oos.writeObject(pal);
                                    oos.close();
                                    break;
                                case 2:
                                    if (patientFile.isFile()) {
                                        ois = new ObjectInputStream(new FileInputStream(patientFile));
                                        pal = (ArrayList<new_patients>) ois.readObject();
                                        ois.close();
                                        boolean found = false;
                                        System.out.println("Enter the Patient ID : ");
                                        pid1 = input.nextInt();
                                        String patpass = "";
                                        li = pal.listIterator();
                                        while (li.hasNext()) {
                                            new_patients p2 = (new_patients) li.next();
                                            if (p2.pid == pid1) {
                                                System.out.println("Patient id found");
                                                System.out.println(p2.patpass);
                                                System.out.println("Enter the password");
                                                patP1 = input.next();
                                                patpass = p2.patpass;
                                                if (patpass.equals(patP1)) {
                                                    System.out.println("logged in successfully ");
                                                    int x = 0;
                                                    boolean patloop = false;
                                                    String patname = "";
                                                    do {
                                                        try {
                                                            do {
                                                                System.out.println("1:View Profile");
                                                                System.out.println("2:Update Profile");
                                                                System.out.println("3:Create appointments");
                                                                System.out.println("4:View appointments");
                                                                System.out.println("5:View Prescription");
                                                                System.out.println("Or Press 0 to go back to login menu");
                                                                int patChoice;
                                                                patChoice = input.nextInt();
                                                                switch (patChoice) {
                                                                    case 0:
                                                                        patsect = 0;
                                                                        //System.out.println(patsect);
                                                                        break;
                                                                    case 1:
                                                                        System.out.println(p2);
                                                                        patname = p2.pname;
                                                                        patsect = 1;
                                                                        break;
                                                                    case 2:
                                                                        while (li.hasNext()) {
                                                                            //new_doctors p2 = (new_doctors) li.next();
                                                                            System.out.print("Enter the new name:-");
                                                                            p2.pname = input.next();
                                                                            System.out.println("Enter room");
                                                                            p2.phone = input.nextInt();
                                                                            li.set(new new_patients(p2.pid, p2.pname, p2.bg, p2.patpass, p2.phone, p2.app, p2.pp));
                                                                            oos = new ObjectOutputStream(new FileOutputStream(patientFile));
                                                                            oos.writeObject(pal);
                                                                            oos.close();
                                                                            System.out.println("Record Edited Successfully!");
                                                                            break;
                                                                        }
                                                                        break;
                                                                    case 3:

                                                                        // First Print the availaible doctors//
                                                                        System.out.println("--------------------------------------------------\n");
                                                                        System.out.println("                 Available Doctors                  \n");
                                                                        ois = new ObjectInputStream(new FileInputStream(doctorFile));
                                                                        dal = (ArrayList<new_doctors>) ois.readObject();
                                                                        ois.close();
                                                                        li = dal.listIterator();
                                                                        ArrayList<String> daknameal = new ArrayList<>();
                                                                        while (li.hasNext()) {
                                                                            new_doctors d = (new_doctors) li.next();
                                                                            if (!d.occ) {
                                                                                System.out.println(d);
                                                                                daknameal.add(d.dname);
                                                                            }
                                                                        }
                                                                        int PatientID, Time, appointNo;
                                                                        String doctorname, Patientname, Date;
                                                                        System.out.println("\n--------------------------------------------------\n");
                                                                        PatientID = p2.pid;
                                                                        appointNo = apal.size() + 1;
                                                                        Patientname = p2.pname;
                                                                        System.out.println("Select one doctor(by name)");
                                                                        doctorname = input.next();
                                                                        if (!daknameal.contains(doctorname)){
                                                                            System.out.println("Wrong doctor selected");
                                                                            System.out.println("Try again");
                                                                            doctorname = input.next();
                                                                        }
                                                                        System.out.println("Enter the time ");
                                                                        Time = input.nextInt();
                                                                        System.out.println("Enter the date ");
                                                                        Date = input.next();
                                                                        boolean confirm = false;
                                                                        String appStatus = "Awaiting";
                                                                        apal.add(new new_appointments(appointNo, PatientID, Patientname, doctorname, Time, Date, confirm, appStatus));
                                                                        oos = new ObjectOutputStream(new FileOutputStream(appointmentFile));
                                                                        oos.writeObject(apal);
                                                                        oos.close();
                                                                        System.out.println("Appointment addedd successfully now \n");
                                                                        System.out.println("Wait for admin to Confirm the appointment");
                                                                        System.out.println(appointNo);
                                                                        patsect = 1;
                                                                        break;
                                                                    case 4:
                                                                        ois = new ObjectInputStream(new FileInputStream(appointmentFile));
                                                                        apal = (ArrayList<new_appointments>) ois.readObject();
                                                                        ois.close();
                                                                        li = apal.listIterator();
                                                                        li1 = apal.listIterator();
                                                                        System.out.println("================== Appointments ================\n");
                                                                        System.out.println("App# Id  Name   Doc  Time  Date     Status \n");
                                                                        while (li.hasNext()) {
                                                                            new_appointments app = (new_appointments) li.next();
                                                                            if (app.PatientID == pid1) {
                                                                                System.out.println(app);
                                                                            }
                                                                        }
                                                                        patsect = 1;
                                                                        break;
                                                                    case 5:
                                                                        ois = new ObjectInputStream(new FileInputStream(prescriptionFile));
                                                                        preal = (ArrayList<new_prescription>) ois.readObject();
                                                                        ois.close();
                                                                        li = preal.listIterator();
                                                                        li1 = dal.listIterator();
                                                                        patname = p2.pname;
                                                                        System.out.println(patname);
                                                                        //System.out.println(preal);
                                                                        new_prescription pres = null;
                                                                        System.out.println("================== Confirmed Prescriptions ================\n");
                                                                        System.out.println("Pre no  App no  Pat name  Doc name   Med   Condition    Ward \t   Bed no \tStatus \n");
                                                                        while (li.hasNext()) {
                                                                            pres = (new_prescription) li.next();
                                                                            if (Objects.equals(pres.Patientname, patname) && pres.preStatus.equals("Prescribed")) {
                                                                                System.out.println(pres);
                                                                                System.out.println("");
                                                                            }
                                                                        }
                                                                        patsect = 1;
                                                                        break;
                                                                }
                                                            } while (patsect == 1);
                                                            patloop = false;
                                                        } catch (Exception e) {
                                                            input.nextLine();
                                                            System.out.println("Invalid input. Try again");
                                                            patloop = true;
                                                        }
                                                    }while (patloop);
                                            /*System.out.println("Press 1 to go back to login menu");
                                            int y = input.nextInt();*/
                                                } else System.out.println("Invalid password");
                                                found = true;
                                            }

                                        }
                                        if (!found) {
                                            System.out.println("Record not found!");
                                            System.out.println("---------------------------------------");
                                        }
                                    } else {
                                        System.out.println("File does not exist!!");
                                    }
                                    break;
                            }
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + lchoice);
                    }
                    if (lchoice == 0){
                        loginloop = false;
                    } else if (choice == 0) {
                        loginloop = true;
                    } else if (docsect == 0) {
                        loginloop = true;
                    } else if (patsect == 0) {
                        loginloop = true;
                    }

            }
            catch(Exception e){
                    input.nextLine();
                    System.out.println("Invalid input try again!");
                    loginloop = true;
                }
            } while (loginloop);


        }

        private static void index (List < String > sg1list, ListIterator li, new_prescription prescription1){
            int sg1_index = sg1list.indexOf("Not occupied");
            prescription1.roomno = String.valueOf(sg1_index + 1);
            prescription1.preStatus = "Prescribed";
            li.set(new new_prescription(prescription1.preNo, prescription1.appointNo, prescription1.Patientname, prescription1.Doctorname, prescription1.medicine, prescription1.condition, prescription1.ward, prescription1.roomno, prescription1.preStatus));
        }
    }
    /*static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}*/