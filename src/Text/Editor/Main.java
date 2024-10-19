package Text.Editor;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    // Stack untuk menyimpan kata-kata yang diinputkan
    private Stack<String> undoStack = new Stack<>();
    // Stack untuk menyimpan kata-kata yang diundo
    private Stack<String> redoStack = new Stack<>();
    // Teks saat ini
    private StringBuilder currentText = new StringBuilder();
    // Scanner untuk membaca input dari pengguna
    private Scanner sc = new Scanner(System.in);

    // Metode untuk menulis teks
    public void write() {
        System.out.print("Masukkan Teks: ");
        String s = sc.nextLine();

        // Periksa apakah teks tidak null dan tidak kosong
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Teks tidak boleh kosong. Silakan masukkan teks yang valid.");
            return;
        }

        // Simpan kata-kata ke dalam undoStack
        for (String word : s.split(" ")) {
            undoStack.push(word);
            currentText.append(word).append(" ");
        }
        
        // Kosongkan redo stack
        redoStack.clear();
        System.out.println("Teks ditambahkan.");
    }

    // Metode untuk menampilkan teks
    public void display() {
        // Cek apakah teks kosong
        if (currentText.length() == 0) {
            System.out.println("Tidak ada yang ditampilkan. Silakan masukkan teks.");
            return;
        }
        
        System.out.println("Teks Saat Ini:");
        System.out.println(currentText.toString().trim());
    }

    // Metode untuk membatalkan aksi terakhir
    public void undo() {
        // Cek apakah ada yang bisa dibatalkan
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa dibatalkan.");
            return;
        }

        // Ambil kata terakhir dari undo stack
        String lastWord = undoStack.pop();
        // Hapus kata terakhir dari currentText
        currentText.setLength(currentText.length() - lastWord.length() - 1); // -1 untuk spasi
        // Simpan kata ke redo stack
        redoStack.push(lastWord);
        System.out.println("Operasi batal dilakukan.");
    }

    // Metode untuk mengulangi aksi yang dibatalkan
    public void redo() {
        // Cek apakah ada yang bisa diulang
        if (redoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa diulang.");
            return;
        }

        // Ambil kata dari redo stack
        String katadariredo = redoStack.pop();
        // Tambahkan kata kembali ke currentText
        currentText.append(katadariredo).append(" ");
        // Simpan kata ke undo stack
        undoStack.push(katadariredo);
        System.out.println("Operasi ulang dilakukan.");
    }

    // Metode untuk menampilkan menu pilihan
    private void displayMenu() {
        System.out.println("1. write - untuk memasukkan teks");
        System.out.println("2. display - untuk menampilkan output");
        System.out.println("3. undo - untuk melakukan operasi batal");
        System.out.println("4. redo - untuk melakukan operasi ulang");
        System.out.println("5. exit - untuk keluar dari editor teks");
    }

    // Metode utama untuk menjalankan program
    public static void main(String[] args) {
        Main editor = new Main(); 
        String ch;

        // Loop untuk terus menampilkan menu sampai pengguna memilih keluar
        while (true) {
            editor.displayMenu(); // Tampilkan menu
            System.out.print("Masukkan Pilihan Anda: ");
            ch = editor.sc.next(); // Baca input pilihan pengguna
            editor.sc.nextLine(); 

            // Proses pilihan pengguna
            switch (ch) {
                case "1":
                    editor.write(); // Memanggil metode untuk menulis teks
                    break;
                case "2":
                    editor.display(); // Memanggil metode untuk menampilkan teks
                    break;
                case "3":
                    editor.undo(); // Memanggil metode untuk undo
                    break;
                case "4":
                    editor.redo(); // Memanggil metode untuk redo
                    break;
                case "5":
                    System.out.println("Terima kasih telah menggunakan editor teks.");
                    return; // Keluar dari program
                default:
                    System.out.println("Masukkan pilihan yang valid."); // Pesan jika pilihan tidak valid
            }
        }
    }
}
