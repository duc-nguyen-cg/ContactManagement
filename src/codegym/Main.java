package codegym;

public class Main {
    public static void printMenu(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\n---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục):");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng: ");
    }


    public static void main(String[] args) {

        ContactManagement manager = new ContactManagement();
        int choice;
        do {
            printMenu();
            choice = InputChecker.inputIntegerInBounds(1,8);
            switch(choice){
                case 1:
                    manager.display(); break;
                case 2:
                    manager.add(); break;
                case 3:
                    manager.edit(); break;
                case 4:
                    manager.remove(); break;
                case 5:
                    manager.search(); break;
                case 6:
                    manager.importData(); break;
                case 7:
                    manager.exportData(); break;
                case 8:
                    System.exit(8);
            }
        } while (choice != 0);
    }


}

