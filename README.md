# Задание №6. Thread pool

В этом задании вам предстоит реализовать собственный пул потоков (thread pool). Создание потока
является затратной операцией, поэтому создание нового потока на каждое асинхронное вычисление
является неоптимальным. Использование же пула потоков позволяет переиспользовать одни и те же потоки
для решения разных задач. Пул помещает все задачи в очередь, из которой свободные потоки
последовательно их извлекают.

## Задачи

### 1. Реализуйте интерфейс ComposableFuture [4 балла]

Класс, реализующий интерфейс `ComposableFuture`, представляет собой обертку над асинхронно
вычисляемым значением. Эта обёртка может быть активной или пассивной (на ваш выбор):

* **активная** содержит в себе логику вычисления значения и предоставляет пулу потоков методы для
  вызова этой логики
* **пассивная** предоставляет интерфейс для установки значения или исключения. В таком случае вам
  необходима ещё одна сущность, манипулирующая вашим `ComposableFuture`.

### 2. Реализуйте интерфейс ThreadPool [4 балла]

Вы можете приступить к реализации `ThreadPool`, имея лишь частичную реализацию `ComposableFuture`.
Унаследуйте свою реализацию от абстрактного класса `ThreadPoolBase`. Рекомендуем выполнять
реализацию в следующем порядке:

* Метод `createWorker`
* Метод `getThreads`
* Метод `invoke`
* Метод `shutdown`
* Метод `awaitFullShutdown`

Обратите внимание на уже реализованный метод `createWorkerThread`, скорее всего будет полезно им
воспользоваться.

### 3. Реализуйте метод thenApply в своей реализации ComposableFuture [2 балла]

## Как сдавать

При создании вашего репозитория будет автоматически создан
[Pull Request для проверки](../../pull/1).

Вы должны закоммитить своё решение в ветку `main` (это ветка вашего репозитория по-умолчанию), и эти
коммиты автоматически добавятся в Pull Request.

**Этот пулл-реквест не нужно мерджить и не нужно закрывать!!!**

Когда вы будете готовы к первой проверке, сделайте следующее:

- Добавьте к этому пулл-реквесту лейбл `ready-for-review`. Это можно сделать в правой части страницы
  с пулл-реквестом в меню `Labels`.
- Отправьте ссылку на этот пулл-реквест в качестве решения
  на [сайте CSC](https://my.compscicenter.ru/).

**Важно:** перед отправкой на проверку убедитесь, что ваш код не содержит замечаний от `reviewdog`!
Просмотреть их вы можете на странице со своим пулл-реквестом.

В дальнейшем, если вы внесли исправления и хотите запросить очередную проверку, просто повторно
запросите ревью от преподавателя, который вас уже проверял:

![Анимация того, как запросить ревью](https://i.stack.imgur.com/H2XaO.gif)

Если у вас возникают проблемы на каком-то из этих шагов, пожалуйста, сообщите об этом в Slack. Чем
быстрее вы это сделаете, тем быстрее мы поможем вам.

## Дополнительные материалы

Несколько рекомендаций по организации многопоточного кода:

- В качестве очереди задач, используемой из нескольких потоков, используйте `BlockingQueue`.
- Для блокировки потоков и их синхронизации можно использовать `synchronized(lockObject)`
  и методы `Object`-а, или `ReentrantLock`.
- В данной работе вы можете использовать атомарные переменные (например, `AtomicBoolean`).

А также несколько предостережений:

- Если ваш поток уснул на локе, то он может случайно проснуться (spurious wakeup) - вы должны
  корректно это обработать.
- Воздержитесь от `synchronized` методов, в данной работе они (скорее всего) вам не потребуются.
- Воздержитесь от использования busy-wait синхронизации.

  Под `busy-wait` мы подразумеваем код типа такого: `while (!flag) { /*пустое тело*/ }`. Такой код является
  неэффективным, поскольку впустую тратит процессорное время и ничего не делает. Вместо этого,
  например, можно использовать связку `Object#wait + Object#notify`.