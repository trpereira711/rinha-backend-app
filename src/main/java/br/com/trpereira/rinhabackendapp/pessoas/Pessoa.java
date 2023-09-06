package br.com.trpereira.rinhabackendapp.pessoas;


import br.com.trpereira.rinhabackendapp.pessoas.utils.StringArrayToStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;


import java.util.UUID;

/**
 * Unused default JavaBeans.
 */
@Entity
@Table(name = "PESSOAS")
public class Pessoa {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()")
    public UUID id;

    @Column(unique = true, length = 32)
    public String apelido;

    @Column(length = 100)
    public String nome;

    @Column(length = 10)
    public String nascimento;

    @JsonDeserialize(using = StringArrayToStringDeserializer.class)
    @Column(columnDefinition = "TEXT")
    public String stack;

    @Column(insertable = false, updatable = false)
    public String busca;

}

