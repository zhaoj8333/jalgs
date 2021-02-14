package phase1.ds.common;

import java.io.Serializable;

/**
 * @author zhaojun
 */
public class Student implements Serializable, Cloneable {
    private String name;
    private Integer age;
    private Skill skill;

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }


    @Override
    public Student clone() throws CloneNotSupportedException {
//        return (Student) super.clone();
        Student stu = (Student) super.clone();
//      使用深拷贝时:   调用Skill clone
        Skill skill = (Skill) stu.getSkill().clone();
        stu.setSkill(skill);

        return stu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Student.class);
        sb.append("{name=");
        sb.append(this.getName());
        sb.append(", ");
        sb.append("age=");
        sb.append(this.getAge());
        sb.append(", ");
        sb.append("skill=");
        sb.append(skill);
        sb.append("}");

        return sb.toString();
    }
}
