import java.util.InputMismatchException;
import java.util.Scanner;

public class TugasPersonalPuspita {
    static final Scanner in = new Scanner ( System.in );
    static MataKuliah[] dataMatkul;

    public static void main(String[] args) {
        boolean run = true;

        while(run) {
            int menu = menuProgram();

            switch (menu){
                case 1:
                    pendataanMatakuliah();
                    break;
                case 2:
                    IPS();
                    break;
                case 3:
                    Grade();
                    break;
                case 4:
                    run = false;
                    break;
            }
        }

        System.out.println("\n-------------------------Terima Kasih-------------------------");
    }

    private static int menuProgram() {
        System.out.println("Pendataan dan Perhitungan IPS (Indeks Prestasi Semester)\n" +
                "1. Pendataan Mata Kuliah\n" +
                "2. Perhitungan IPS\n" +
                "3. Update Grade\n" +
                "4. Keluar");

        int dataMenu = 0;

        while (dataMenu < 1 || dataMenu > 4) {
            try{
                System.out.print("Inputkan menu: ");
                dataMenu = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Mohon maaf menu yang anda inputkan salah");
            }
            in.nextLine();
        }

        return dataMenu;
    }

    private static void pendataanMatakuliah() {
        if (dataMatkul != null) {
            dataMatkul = null;
        }

        System.out.println("----------------Silahka Isi Data Dibawah Ini----------------");

        int jumlah, sks;
        String kodeMatKul, namaMatKul, gradeMatKul;
        boolean validasi = false;
        jumlah = 0;
        sks = 0;
        gradeMatKul ="";

        while (!validasi) {
            try {
                System.out.print("inputkan jumlah matkul \t: ");
                jumlah = in.nextInt();
                validasi = true;
            } catch (InputMismatchException e) {
                System.out.println("Mohon maaf anda salah menginputkan jumlah matkul");
            }
            in.nextLine();
        }

        dataMatkul = new MataKuliah[jumlah];

        for (int i = 0; i < jumlah; i++) {
            System.out.print("\nInputkan kode matkul \t: ");
            kodeMatKul = in.next();

            System.out.print("Inputkan nama matkul \t: ");
            namaMatKul = in.next();

            validasi =false;
            while (!validasi) {
                try {
                    System.out.print("Inputkan jumlah sks \t: ");
                    sks = in.nextInt();
                    validasi = true;
                } catch (InputMismatchException e) {
                    System.out.println("Mohon maaf anda salah menginputkan jumlah sks");
                }
                in.nextLine();
            }

            validasi = false;
            while (!validasi) {
                System.out.print("Inputkan Grade \t: ");
                gradeMatKul = in.next();
                if("ABCDE".contains(gradeMatKul)){
                    validasi = true;
                }else{
                    System.out.println("Anda salah menginputkan grade, silahkan menginputkan grade A,B,C,D, atau E ");
                }
                in.nextLine();
            }

            System.out.println("------------------------------------------");

            MataKuliah matkul= new MataKuliah();
            matkul.setGradeMatKul(gradeMatKul);
            matkul.setSks(sks);
            matkul.setKodeMatkul(kodeMatKul);
            matkul.setNamaMatKul(namaMatKul);

            dataMatkul[i] = matkul;
        }

        System.out.println("Selamat! Anda sukses melakukan pendaataan mata kuliah");
    }

    private static void Grade() {

        if (dataMatkul == null) {
            System.out.println("Mohon maaf data kosong \n");
            return;
        }

        String kodeMatKul;
        String gradeMatKul = "";
        Boolean validasi=false;

        System.out.print("Inputkan kode mata kuliah: ");
        kodeMatKul = in.next();

        for (int i = 0; i < dataMatkul.length; i++) {
            String getKodeMatKul = dataMatkul[i].getKodeMatkul();

            if (getKodeMatKul.equals(kodeMatKul)) {
                while (!validasi) {
                    System.out.print("Inputkan grade baru: ");
                    gradeMatKul = in.next();
                    if("ABCDE".contains(gradeMatKul)){
                        validasi = true;
                    }else{
                        System.out.println("Anda salah menginputkan grade, masukkan grade A,B,C,D, atau E ");
                    }
                    in.nextLine();
                }

                dataMatkul[i].setGradeMatKul(gradeMatKul);

                System.out.println("Selamat! Anda sukses Update Grade Anda \n");
            }
        }
    }

    private static void IPS() {
        if (dataMatkul == null) {
            System.out.println("Mohon maaf data kosong \n");
            return;
        }

        int totalSks = 0;
        float ips = 0;
        float NilaiIps;
        int sks, grade;
        String kodeMatkul, namaMatKul, gradeMatKul;

        System.out.println("List mata kuliah yang anda ambil adalah: ");
        for (int i = 0; i < dataMatkul.length; i++) {
            namaMatKul = dataMatkul[i].getNamaMatKul();
            kodeMatkul = dataMatkul[i].getKodeMatkul();
            gradeMatKul = dataMatkul[i].getGradeMatkul().toUpperCase();
            sks = dataMatkul[i].getSks();

            System.out.println(kodeMatkul + "\t" + namaMatKul + "\t" + gradeMatKul + "\t" + sks);

            switch (gradeMatKul){
                case "A":
                    grade = 4;
                    break;
                case "B":
                    grade = 3;
                    break;
                case "C":
                    grade = 2;
                    break;
                case "D":
                    grade = 1;
                    break;
                case "E":
                    grade = 0;
                    break;
                default:
                    grade = 0;
            }

            totalSks += sks;

            ips += (sks * grade);
        }

        NilaiIps = ips / totalSks;

        System.out.println("Nilai Indeks Prestasi Semester Anda adalah: " + NilaiIps);
        System.out.println("");
    }

    public static class MataKuliah {
        int sks;
        String gradeMatKul, namaMatKul, kodeMatkul;

        public int getSks() {
            return sks;
        }

        public String getGradeMatkul() {
            return gradeMatKul;
        }

        public String getKodeMatkul() {
            return kodeMatkul;
        }

        public String getNamaMatKul() {
            return namaMatKul;
        }

        public void setSks(int sks) {
            this.sks = sks;
        }

        public void setGradeMatKul(String gradeMatKul) {
            this.gradeMatKul = gradeMatKul;
        }

        public void setKodeMatkul(String kodeMatkul) {
            this.kodeMatkul = kodeMatkul;
        }

        public void setNamaMatKul(String namaMatKul) {
            this.namaMatKul = namaMatKul;
        }
    }

}
