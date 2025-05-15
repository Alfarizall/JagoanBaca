package com.example.crud.controller;

import com.example.crud.Model.Mahasiswa;
import com.example.crud.repository.MahasiswaRepository;

import com.example.crud.Model.Mahasiswa;
import com.example.crud.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    @Autowired
    private MahasiswaRepository repo;

    // Tampilkan daftar mahasiswa
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("mahasiswa", repo.findAll());
        return "index";
    }

    // Tampilkan form tambah mahasiswa
    @GetMapping("/tambah")
    public String add(Model model) {
        model.addAttribute("mahasiswa", new Mahasiswa());
        return "tambah";
    }
    // Proses tambah mahasiswa
    @PostMapping("/tambah")
    public String add(@ModelAttribute Mahasiswa mahasiswa) {
        System.out.println("Menyimpan Mahasiswa: " + mahasiswa.getNrp() + " - " + mahasiswa.getNama());
        repo.save(mahasiswa);
        return "redirect:/mahasiswa/";
    }

    // Tampilkan form edit mahasiswa
    @GetMapping("/edit/{nrp}")
    public String edit(@PathVariable String nrp, Model model) {
        Mahasiswa mahasiswa = repo.findById(nrp).orElse(null);
        model.addAttribute("mahasiswa", mahasiswa);
        return "edit";
    }
    // Proses edit mahasiswa
    @PostMapping("/edit/{nrp}")
    public String edit(@PathVariable String nrp, @ModelAttribute Mahasiswa mahasiswa) {
        repo.save(mahasiswa);
        return "redirect:/mahasiswa/";
    }
    // Proses hapus mahasiswa
    @GetMapping("/delete/{nrp}")
    public String delete(@PathVariable String nrp) {
        repo.deleteById(nrp);
        return "redirect:/mahasiswa/";
    }
    
}
