package cs.go.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
//Узнать что такое uniqueConstraints = {@UniqueConstraint(columnNames = "chat_id", name = "users_unique_chatid_idx"
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity {

    @Column(name = "chat_id", unique = true, nullable = true)
    @NotNull
    private Long chatId;
    @Column(name = "name", nullable = true)
    @NotBlank
    private String name;
    @Column(name = "score", nullable = true)
    @NotNull
    private Integer score;
    @Column(name = "high_score", nullable = true)
    @NotNull
    private Integer highScore;
    @Column(name = "bot_state", nullable = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private State botState;

    // Проверить в дальнейшем поле name ()
    public User(long chatId) {
        this.chatId = chatId;
        this.name = String.valueOf(chatId);
        this.score = 0;
        this.highScore = 0;
        this.botState = State.START;
    }
}




