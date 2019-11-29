package com.sentimentanalysis.sentimentanalysis;

import hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class MainController {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;
    // for python staff
    @Autowired
    @Qualifier("helloServicePython")
    private HelloService service;

    // login
    @PostMapping("/users/authenticate")
    public @ResponseBody
    ResponseEntity<User> saveText ( @RequestBody UserLoginDto userLoginDto) {

        User u = userRepository.findByUsernameAndPassword(userLoginDto.getUsername(), userLoginDto.getPassword());
        System.out.println("Znaleziony user w bazie: " + u);
        if(u==null){
            // UWAGA: poprawic, zeby wysylalo tez body: 'Invalid username or password' - na razie nie dziala -wywala sie na UI
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

       return ResponseEntity.ok(u);
    }

    @PostMapping("/users/register")
    public @ResponseBody
    ResponseEntity registerUser ( @RequestBody UserRegisterDto userRegisterDto) {

        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setToken("blablablablabla"); // GENERATE TOKEN

        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    // for angular example
    @PostMapping("/new")
    public @ResponseBody
    List saveText ( @RequestBody VaderFormDto vaderFormDto) {

        User u = vaderFormDto.getUser();

        Timestamp t = new Timestamp(System.currentTimeMillis());
        Entry e = new Entry();
        e.setText(vaderFormDto.getText());
        e.setTimestamp(t);
        e.setUser(u);
        entryRepository.save(e);

        // polaczenie z pythonem
        // python chyba powinien zwracac slownik: vader: [1,1,1] slowosiec:[2,2,2]
        List pythonResults = service.get_result();
//        System.out.println(pythonResults);
//        List pythonResultDtoList = new LinkedList<>();
//        for(List l:pythonResults){
//            System.out.println(l);
////            for(Object a:l){
////                System.out.println(a.toString());
////            }
//            PythonResultDto pythonResultDto = new PythonResultDto();
//            pythonResultDto.setMethod(l.get(0).toString());
//            pythonResultDto.setPositive((int) l.get(1));
//            pythonResultDto.setNegative((int) l.get(2));
//            pythonResultDto.setNeutral((int) l.get(3));
//            pythonResultDto.setOverall((int) l.get(4));
//            pythonResultDtoList.add(pythonResultDto);
//        }
        System.out.println(pythonResults);
        // zapisanie result do bazy
        Result r = new Result();
        r.setEntry(e);
        r.setMethon("vader");
        r.setPositive(1);
        r.setNegative(1);
        r.setNeutral(1);
        r.setOverall(1);
        resultRepository.save(r);

        Long a = Long.valueOf(2);

        // polaczenie z tabela history
        // zmiana schematu bazy danych?


        VaderAnswerDto answerDto = new VaderAnswerDto();
        LinkedList response =new LinkedList();
        response.add(1);
        answerDto.setAnswer(response);

        System.out.println(u);
        System.out.println(e);
        System.out.println(r);

        return pythonResults;
    }

    // for python example - odbiera dane wyslane z pythona
    @PostMapping("/save-vader")
    public @ResponseBody
        // incorrect but for python example
    void saveText ( @RequestBody String vaderFormDto) {
        System.out.println(vaderFormDto);
        System.out.println("My response");
    }

    @GetMapping(path="/json")
    public @ResponseBody Iterable getAllEntries() {
        // This returns a JSON or XML with the users
        List pythonResults = service.get_result();
        return pythonResults;
}

}

//    @Autowired
//    @Qualifier("helloServicePython")
//    private HelloService service;
//
//    @RequestMapping("/paulina")
//    public List index() {
//        return service.get_result();
//    }

//Examples that may be useful one day, so I still keep it
//@GetMapping(path="/all")
//public @ResponseBody Iterable<Entry> getAllEntries() {
//    // This returns a JSON or XML with the users
//    return entryRepository.findAll();
//}
