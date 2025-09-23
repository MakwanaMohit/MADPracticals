import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(

        colorScheme: ColorScheme.fromSeed(seedColor: Colors.red),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: ''),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
  TextEditingController _no1 = TextEditingController();
  TextEditingController _no2 = TextEditingController();
  TextEditingController _ans = TextEditingController();

  return Scaffold(
      appBar: AppBar(

        backgroundColor: Theme.of(context).colorScheme.inversePrimary,

        title: Text(widget.title),
      ),
      body: Center(

        child: Column(

          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
              SizedBox(
                width: 400,
                child: TextField(
                    controller: _no1,
                    decoration: InputDecoration(border: OutlineInputBorder(),
                    hintText: "Enter No-1"
                    ),
                ),
              ),
            SizedBox(
              width: 400,
              child: TextField(
                controller: _no2,
                decoration: InputDecoration(border: OutlineInputBorder(),
                    hintText: "Enter No-2"
                ),
              ),
            ),
            ElevatedButton(
                  onPressed: () {
                      int sum = int.parse(_no1.text)  + int.parse(_no2.text);
                      _ans.text = sum.toString();

                  },
              style: const ButtonStyle(
                backgroundColor: MaterialStatePropertyAll<Color>(Colors.green),
              ),
              child: Text('Add'),
            ),
            SizedBox(
              width: 400,
              child: TextField(
                controller: _ans,
                decoration: InputDecoration(border: OutlineInputBorder(),
                    hintText: "Ans"
                ),
              ),
            )


          ],
        ),
      ),
       // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
