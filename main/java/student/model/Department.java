package student.model;

public enum Department {
    COMPUTER_SCIENCE("Computer Science", "CS"),
    MATHMATICS("Mathematics", "MATH"),
    PHYSICS("Physics", "PHY"),
    CHEMISTRY("Chemistry", "CHEM"),
    BIOLOGY("Biology", "BIO"),
    ELECTRICAL_ENGINEERING("Electrical Engineering", "EE"),
    MECHANICAL_ENGINEERING("Mechanical Engineering", "ME"),
    BUSINESS_ADMINISTRATION("Business Administration", "BA"),
    ENGLISH("English Literature", "ENG"),
    ;

    private final String fullName;
    private final String code;

    Department(String fullName, String code) {
        this.fullName = fullName;
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCode() {
        return code;
    }

    public static Department fromCode(String code) {
        for (Department dept : Department.values()) {
            if (dept.getCode().equalsIgnoreCase(code)) {
                return dept;
            }
        }

        /**
         *  runtime exception, Use IllegalArgumentException for bad input
         *  */
        throw new IllegalArgumentException("Invalid department code: " + code);
    }

    public static Department fromFullName(String fullName) {
        for (Department dept : Department.values()) {
            if (dept.getFullName().equalsIgnoreCase(fullName)) {
                return dept;
            }
        }
        throw new IllegalArgumentException("Invalid department full name: " + fullName);
    }

    public boolean isSTEM(){
        /** this refers the current enum constant in calling the method
         * */

        return this == COMPUTER_SCIENCE || this == MATHMATICS ||
                this == PHYSICS || this == CHEMISTRY
                || this == BIOLOGY || this == ELECTRICAL_ENGINEERING;
    }

     @Override public String toString(){
        return fullName + "( " + code +")";
     }
}
