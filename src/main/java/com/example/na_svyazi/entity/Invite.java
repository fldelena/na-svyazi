package com.example.na_svyazi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Table(name = "invite")
@Entity
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    public Invite(){
    }
    Invite(User user){
        this.fromUser = user;
    }

    @Override
    public String toString() {
        return "Invite{" +
                "id=" + id +
                ", fromUser=" + fromUser.getId() + " " + fromUser.getName() +
                '}';
    }
}
