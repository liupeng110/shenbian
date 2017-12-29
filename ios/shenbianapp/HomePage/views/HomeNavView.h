//
//  HomeNavView.h
//  Miaohi
//
//  Created by   on 17/5/8.
//  Copyright © 2017 haiqiu. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^GoShoppingCart)(void);

typedef void(^LocationSearch)(void);

@interface HomeNavView : UIView

@property(nonatomic,strong)UIButton *locationButton;
@property(nonatomic,strong)NSArray *quikSearchList;
@property(nonatomic,strong)GoShoppingCart goShoppingCart;
@property(nonatomic,strong)LocationSearch locationSearch;
@end
