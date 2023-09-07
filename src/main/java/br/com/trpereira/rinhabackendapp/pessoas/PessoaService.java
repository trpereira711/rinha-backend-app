package br.com.trpereira.rinhabackendapp.pessoas;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final BlockingQueue<Pessoa> queue = new ArrayBlockingQueue<>(50000);
    private final Set<String> uniquePropertyCache = Collections.synchronizedSet(new HashSet<>());


    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public void put(Pessoa pessoa) throws InterruptedException {
        String apelido = pessoa.apelido;
        if (!uniquePropertyCache.contains(apelido)) {
            uniquePropertyCache.add(apelido);
            queue.put(pessoa);
        }
    }

    @Scheduled(fixedRate = 1000)
    public void insertQueue() throws InterruptedException {
        List<Pessoa> batch = new ArrayList<>();

        while (!queue.isEmpty() && batch.size() < 2000) {
            batch.add(queue.take());
        }

        saveBatch(batch);
    }

    private void saveBatch(List<Pessoa> batch) {
        if (batch.isEmpty()) {
            return;
        }

        try {
            repository.saveAll(batch);
        } catch (Exception e) {
            int mid = batch.size() / 2;
            List<Pessoa> firstHalf = batch.subList(0, mid);
            List<Pessoa> secondHalf = batch.subList(mid, batch.size());

            saveBatch(firstHalf);
            saveBatch(secondHalf);
        }
    }

}
