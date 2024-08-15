INF01120 Técnicas de Construção de Programas
Semestre: 2024/1
Professor: Marcelo Soares Pimenta
Descrição:

1.1 OBJETIVO
    O objetivo principal do programa é gerar uma música a partir de um arquivo de texto
fornecido pelo usuário.

1.2 CLASSES E MÓDULOS
• Leitura de texto - Responsável por ler o texto de entrada que será convertido em
música.

• Processamento de texto - Converte o texto lido em notas musicais e parâmetros
musicais conforme o mapeamento estabelecido.

• Elementos Musicais - Define os tipos de elementos musicais (notas, pausas,
alterações de volume e instrumentos).

• Síntese de Áudio - Responsável por converter os elementos musicais em sons
utilizando uma biblioteca de áudio.

• Interface do Usuário - Fornece a interface para entrada de texto e controle do
programa.

• Módulo Principal - Gerencia a interação entre os outros módulos e coordena o
fluxo do programa.

Módulo Leitura de texto
TextReader: Classe responsável por ler o texto de uma fonte (e.g., arquivo, campo
de texto na interface).

Módulo de Processamento de Texto
TextProcessor: Classe responsável pelo processamento do texto.

Módulo de Elementos Musicais
MusicalElement: Classe base para todos os elementos musicais.
Note: Classe derivada de MusicalElement para representar uma nota.
Pause: Classe derivada de MusicalElement para representar uma pausa.
VolumeChange: Classe derivada de MusicalElement para representar mudanças de
volume.
InstrumentChange: Classe derivada de MusicalElement para representar mudanças
de instrumento.

Módulo de Síntese de Áudio
AudioSynthesizer: Classe que utiliza a biblioteca de áudio para tocar a música.

Módulo de Interface do Usuário
UserInterface: Classe responsável pela interface do usuário.

Bibliotecas usadas:
• JUnit (tests)
• JFugue (manipulação e tocador de música)
• JavaFX ( GUI)