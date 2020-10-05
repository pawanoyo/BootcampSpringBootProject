namespace java com.example.demo

//typedef i32 int // We can use typedef to get pretty names for the types we are using
//
//struct Custm{
//    01: int custmId;
//}
//
//service MultiplicationService
//{
//        int multiply(1:int n1, 2:int n2),
//        void addCust(1:Custm c),
//        Custm getCustId(),
//}

typedef i32 int // We can use typedef to get pretty names for the types we are using
service MultiplicationService
{
        int multiply(1:int n1, 2:int n2),
}