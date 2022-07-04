import 'package:authentication_repository/authentication_repository.dart';
import 'package:abcleaver/app/nav.dart';
import 'package:abcleaver/commons/constants/routes.dart';
import 'package:abcleaver/commons/widgets/loading_indicator.dart';
import 'package:abcleaver/features/authentication/authentication.dart';
import 'package:abcleaver/features/login/view/login_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BlocConsumer<AuthenticationBloc, AuthenticationState>(
      listener: (context, state) =>
          debugPrint("[LANDING PAGE] Receiving event: $state"),
      builder: (context, state) {
        if (state.status == AuthenticationStatus.unauthenticated ||
            state.status == AuthenticationStatus.unknown) {
          return const LoginPage();
        } else if (state.status == AuthenticationStatus.authenticated) {
          return const NavBarPage(initialPage: Routes.home);
        } else {
          return const LoadingIndicator();
        }
      },
    );
  }
}
