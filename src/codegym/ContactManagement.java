package codegym;


import java.io.*;
import java.util.*;

public class ContactManagement {
    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contactList = new ArrayList<>();

    public final String EMPTY_MESSAGE = "Danh bạ này trống!"
            , NOT_FOUND_MESSAGE = "Không tìm được danh bạ với số điện thoại trên."
            , CANCEL_MESSAGE = "Thao tác bị hủy!"
            , COMMA = ",", EMPTY_STRING = "";

    public void display(){
        System.out.println("Toàn bộ danh bạ: ");
        print(contactList);
    }


    public void add(){
        Contact newContact = new Contact();
        newContact.inputInfo();
        contactList.add(newContact);
        System.out.println("Thêm mới thành công!");
    }


    public void remove(){
        if (contactList.isEmpty()){
            System.err.println(EMPTY_MESSAGE); return;
        }
        Contact found = inputSearch();
        if (found == null){ return; }

        System.out.println(found);
        System.out.println("Xóa danh bạ trên? ");
        System.out.println(" Nhập Y để xác nhận: ");
        String choice = scanner.nextLine();
        if (choice.equals("Y")){
            contactList.remove(found);
            System.out.println("Xóa thành công!");
        } else {
            System.err.println(CANCEL_MESSAGE);
        }
    }



    public void edit(){
        if (contactList.isEmpty()){
            System.err.println(EMPTY_MESSAGE); return;
        }
        Contact found = inputSearch();
        if (found == null){ return; }

        System.out.println("Danh bạ cũ: "+found);
        found.editInfo();
        System.out.println("Chỉnh sửa thành công!");
    }


    public void search(){
        if (contactList.isEmpty()){
            System.err.println(EMPTY_MESSAGE); return;
        }
        System.out.println("Tìm kiếm theo:   1.Số điện thoại   2.Họ tên");
        int choice = InputChecker.inputIntegerInBounds(1,2);
        List<Contact> found;
        if (choice == 1){
            found = searchByPhone();
        } else {
            found = searchByName();
        }

        if (found.isEmpty()){
            System.err.println(NOT_FOUND_MESSAGE);
        } else {
            print(found);
        }
    }



    public void importData(){
        System.out.println("Thao tác này sẽ cập nhật lại toàn bộ danh bạ cũ");
        System.out.println("Nhập Y để xác nhận: ");
        String choice = scanner.nextLine();
        if (!choice.equals("Y")){
            System.err.println(CANCEL_MESSAGE); return;
        }

        File file = new File("data/contacts.csv");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            List<Contact> readData = new ArrayList<>();

            reader.readLine();   //skip the header
            while( (line = reader.readLine()) != null){
                String[] row = line.split(COMMA);
                String phone = row[0].trim();
                String group = row[1].trim();
                String name = row[2].trim();
                String gender = row[3].trim();
                String address = row[4].trim();
                String birthDate = row[5].trim();
                String email = row[6].trim();
                readData.add(new Contact(phone, group, name, gender, address, birthDate, email));
            }
            contactList = readData;
            reader.close();
            System.out.println("Đọc file thành công!");
        } catch (FileNotFoundException e) {
            System.err.println("Không tìm thấy file!");
        } catch (Exception e){
            System.err.println("Không đọc được file!");
        }
    }


    public void exportData(){
        if (contactList.isEmpty()){
            System.err.println(EMPTY_MESSAGE); return;
        }
        System.out.println("Thao tác này sẽ ghi đè dữ liệu cũ trong file");
        System.out.println("Nhập Y để xác nhận: ");
        String choice = scanner.nextLine();
        if (!choice.equals("Y")){
            System.err.println(CANCEL_MESSAGE); return;
        }

        File file = new File("data/contacts.csv");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            //write header
            writer.write("Số Điện Thoại"+ COMMA+"Nhóm" +COMMA + "Họ Tên"+COMMA+"Giới Tính"+COMMA+"Địa Chỉ"+COMMA+"Ngày Sinh"+COMMA+"Email\n");
            //write content
            for (Contact contact: contactList){
                writer.write(contact.toCSV());
            }
            writer.close();
            System.out.println("Ghi file thành công!");
        } catch (Exception e) {
            System.err.println("Lỗi, không thể ghi vào file!");
        }
    }




    private Contact inputSearch(){
        String inputPhone;
        Contact found;
        do {
            System.out.println("Nhập số điện thoại cần tìm: ");
            inputPhone = scanner.nextLine();
            if (inputPhone.equals(EMPTY_STRING)){
                return null;
            }
            found = searchContactByPhone(contactList, inputPhone);
        } while (found == null);
        return found;
    }



    private Contact searchContactByPhone(List<Contact> list, String phone){
        for (Contact contact : list){
            if (contact.getPhone().equals(phone)){
                return contact;
            }
        }
        System.err.println(NOT_FOUND_MESSAGE);
        return null;
    }



    private List<Contact> searchByPhone(){
        System.out.println("Nhập số điện thoại để tìm kiếm: ");
        String inputPhone = scanner.nextLine();

        List<Contact> found = new ArrayList<>();
        for (Contact contact: contactList){
            if (!inputPhone.equals(EMPTY_STRING) && contact.getPhone().contains(inputPhone)){
                found.add(contact);
            }
        }
        return found;
    }


    private List<Contact> searchByName(){
        System.out.println("Nhập họ tên để tìm kiếm: ");
        String inputName = scanner.nextLine();
        List<Contact> found = new ArrayList<>();
        for (Contact contact: contactList){
            if (!inputName.equals(EMPTY_STRING) && contact.getName().trim().toLowerCase().contains(inputName.trim().toLowerCase())){
                found.add(contact);
            }
        }
        return found;
    }



    private void print(List<Contact> list){
        if (list.isEmpty()){
            System.err.println(EMPTY_MESSAGE);
        }
        for (Contact contact: list){
            System.out.println(contact);
        }
    }


}
