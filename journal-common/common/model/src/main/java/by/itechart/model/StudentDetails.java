package by.itechart.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_details")
public class StudentDetails {

    @Id
    @SequenceGenerator(name = "details_seq", sequenceName = "details_seq",
            allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "details_seq")
    private Long id;

    @Column(name = "first_parent")
    private String firstParent;

    @Column(name = "second_parent")
    private String secondParent;

    @Column(name = "parents_email")
    private String parentsEmail;

    @Column(name = "contact_num")
    private String contactNum;

    @Column
    private String bio;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public StudentDetails(String firstParent, String secondParent,
                          String parentsEmail, String contactNum, String bio) {
        this.firstParent = firstParent;
        this.secondParent = secondParent;
        this.parentsEmail = parentsEmail;
        this.contactNum = contactNum;
        this.bio = bio;
    }

    public StudentDetails(String firstParent, String parentsEmail,
                          String contactNum, String bio) {
        this.firstParent = firstParent;
        this.parentsEmail = parentsEmail;
        this.contactNum = contactNum;
        this.bio = bio;
    }
}
