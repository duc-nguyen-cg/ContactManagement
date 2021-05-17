package codegym;

public class Contact {
    private final int MAX_STRING_LENGTH = 30;
    private final String EMAIL_REGEX = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[a-z]+$", PHONE_REGEX = "^[0-9]{10}$"
            , EMAIL_DESCRIPTION = "Email gồm các kí tự chữ hoặc số, có @ và dấu chấm, ví dụ duc@gmail.com"
            , PHONE_DESCRIPTION = "Số điện thoại có 10 kí tự số, ví dụ 0912345678";


    private String phone, group, name, gender, address, birthDate, email;

    public Contact() {
    }

    public Contact(String phone, String group, String name, String gender, String address, String birthDate, String email) {
        this.phone = phone;
        this.group = group;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void inputInfo(){
        System.out.println("Số điện thoại: ");
        phone = InputChecker.inputString(PHONE_REGEX, PHONE_DESCRIPTION);
        editInfo();
    }


    public void editInfo(){
        System.out.println("Nhóm: ");
        group = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Họ tên: ");
        name = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Giới tính: ");
        gender = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Địa chỉ: ");
        address = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Ngày sinh: ");
        birthDate = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Email: ");
        email = InputChecker.inputString(EMAIL_REGEX, EMAIL_DESCRIPTION);
    }


    @Override
    public String toString() {
        return "Số điện thoại = " + phone +
                ", Nhóm = " + group +
                ", Họ tên = " + name +
                ", Giới tính = " + gender +
                ", Địa chỉ = " + address ;
    }


    public String toCSV(){
        return phone + "," +
                group + "," +
                name + "," +
                gender + "," +
                address + "," +
                birthDate + "," +
                email + "\n";
    }

}
