# API para tábua de maré
## Intro
Hoje em dia existe uma dificuldade muito grande em integrar serviços de tábua de maré em nossas aplicações. Os serviços disponíveis possuem documentação ruim e muitas vezes não funcionam bem. Por isso criei esse scrapper que transforma o HTML da tábua de maré disponibilizada pelo [cptec](http://ondas.cptec.inpe.br/) em uma resposta JSON.

## Contrato
Para fazer a chamada teremos que passar três parâmetros:
* regionCode = Código da região da tábua de maré
* month = Número do mês de 01 a 12
* year = Número do ano em 2 digitos

Exemplo de chamada:
```
tidelevel?regionCode=30461&month=01&year=19
```

Exemplos de código de região:
```
60135::Barra do Porto de Paranaguá Canal da Galheta::PR
60130::Barra do Porto de Paranaguá Canal Sueste::PR
10653::Barra Norte do Rio Amazonas-Ponta do Céu::AP
30825::Capitania dos Portos de Sergipe::SE
20520::Fundeadouro de Salinópolis::PA
40263::Ilha da Trindade::Brasil
30955::Ilha de Fernando de Noronha::Pernambuco
10525::Ilha do Mosqueiro::PA
20535::Ilha dos Guarás::PA
50170::Porto de Angra dos Reis::RJ
40135::Porto de Aratu::BA
30407::Porto de Areia Branca-Termisa::RN
10520::Porto de Belém::PA
30540::Porto de Cabedelo::PB
60245::Porto de Florianópolis::SC
30443::Proto de Guamaré::RN
40145::Porto de Ilhéus::BA
60250::Porto de Imbituba::SC
50145::Porto de Itaguaí::RJ
60235::Porto de Itajaí::SC
30110::Porto de Itaqui::MA
30225::Porto de Luís Correia::PI
30445::Porto de Macau::RN
30725::Porto de Maceió::AL
40118::Porto de Madre de Deus::BA
30340::Porto de Mucuripe::CE
30461::Porto de Natal::RN
60132::Porto de Paranaguá::PR
30645::Porto de Recife::PE
40140::Porto de Salvador::BA
10615::Porto de Santana-Icomi::AM
50225::Porto de Santos::SP
60220::Porto de São Francisco do Sul::SC
50210::Porto de São Sebastião::SP
30685::Porto de Suape::PE
30140::Porto de Tutóia::MA
10566::Porto de Vila do Conde::PA
40252::Porto de Vitória::ES
50156::Porto do Forno::RJ
50140::Porto do Rio de Janeiro::RJ
60370::Porto do Rio Grande::RS
40255::Porto do Tubarão::ES
30120::São Luís::MA
30114::Terminal da Alumar::MA
50165::Terminal da Ilha Guaíba::RJ
30149::Terminal da Ponta da Madeira::MA
40280::Terminal da Ponta do Ubu::ES
40240::Terminal de Barra do Riacho::ES
50116::Terminal Marítimo de Imbetiba::RJ
30810::Terminal Marítmo Inácio Barbosa::SE
60139::Terminal Portuário da Ponta do Félix::PR
30337::Terminal Portuário de Pecem::CE
10572::Trapiche de Breves::PA
```

Exemplo de resposta:
```
[
  {
    "date": "01/01/19",
    "levelList": [
      {
        "hour": "01:00",
        "level": "2.0"
      },
      {
        "hour": "07:00",
        "level": "0.6"
      },
      {
        "hour": "13:30",
        "level": "2.0"
      },
      {
        "hour": "19:30",
        "level": "0.6"
      }
    ]
  },
  {
    "date": "02/01/19",
    "levelList": [
      {
        "hour": "01:58",
        "level": "2.0"
      },
      {
        "hour": "07:53",
        "level": "0.5"
      },
      {
        "hour": "14:19",
        "level": "2.1"
      },
      {
        "hour": "20:19",
        "level": "0.5"
      }
    ]
  },
  ...
]
```

## Pontos de melhoria no projeto
* Melhoria nos testes com aplicação de ferramenta de injeção de dependência
* Criação de uma pipeline de integração contínua
* Deploy da API em um endpoint público
