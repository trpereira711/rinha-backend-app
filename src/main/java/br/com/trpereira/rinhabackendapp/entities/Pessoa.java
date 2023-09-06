package br.com.trpereira.rinhabackendapp.entities;

import br.com.trpereira.rinhabackendapp.StringListConverter;
import br.com.trpereira.rinhabackendapp.interfaces.form.PessoaForm;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "PESSOAS")
public class Pessoa {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "apelido", nullable = false, unique = true, length = 32)
    private String apelido;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name = "stack", columnDefinition = "character varying[]")
    @Convert(converter = StringListConverter.class)
    private List<String> stack;

    public Pessoa() {
        this.stack = new ArrayList<>();
    }

    private Pessoa(PessoaForm pessoaDTO) {
        this.apelido = pessoaDTO.apelido();
        this.nome = pessoaDTO.nome();
        this.nascimento = pessoaDTO.nascimento();
        this.stack = pessoaDTO.stack();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", apelido='" + apelido + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                ", stack=" + stack +
                '}';
    }

    public static Pessoa of(PessoaForm pessoaForm) {
        return new Pessoa(pessoaForm);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return Collections.unmodifiableList(stack);
    }

    public void addStack(String stackItem) {
        if (stackItem != null) {
            this.stack.add(stackItem);
        }
    }
}
