package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex2.entity.Memo;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass(){
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Memo memo = Memo.builder().memoText("Memo.." + i).build();

            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("===============================");

        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void testSelect2() {
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);

        System.out.println("===============================");

        System.out.println(memo);
    }

    @Test
    public void testUpdate(){
        Memo memo = Memo.builder().mno(1L).memoText("Update.........1").build();

        System.out.println("1----------------------------------");
        memoRepository.save(memo);
        System.out.println("2----------------------------------");
    }


    @Test
    public void testDelete(){
        Long mno = 100L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){

        Sort sort = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0,10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);

    }

    @Test
    public void testSort(){
        Sort sort = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0,10, sort);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo->{
            System.out.println(memo);
        });

    }
}
