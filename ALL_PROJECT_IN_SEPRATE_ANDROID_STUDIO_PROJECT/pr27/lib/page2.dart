import 'package:flutter/material.dart';

class page2 extends StatelessWidget {
  String data;

  page2({
    required this.data
});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Page 2"),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Text(
                style: TextStyle(
                  color: Colors.red,
                ),
                "Welcome to page 2"+data
            )
          ],
        ),
      ),
    );
  }
}